package pl.pniedziela.user;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.Map.Entry;

import javax.imageio.ImageIO;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.pniedziela.email.EmailService;
import pl.pniedziela.logs.Log;
import pl.pniedziela.logs.LogService;
import pl.pniedziela.logs.LogTypes;
import pl.pniedziela.uploader.FileUploadService;
import pl.pniedziela.uploader.UploadFile;
import pl.pniedziela.user.remindpass.RemindPassService;
import pl.pniedziela.user.role.Role;
import pl.pniedziela.user.role.RoleService;
import pl.pniedziela.user.social.UserGoogle;

@Service
@EnableAsync
@Transactional
public class UserService {

	@Autowired
	UserDao userDao;
	@Autowired
	StandardPasswordEncoder passwordEncoder;
	@Autowired
	RoleService roleService;
	@Autowired
	RemindPassService remindPassService;
	@Autowired
	EmailService emailService;
	@Autowired
	LogService logService;
	@Autowired
	FileUploadService fileUploadService;

	public boolean checkExistsByNickname(String nickname) {

		return userDao.findByNickname(nickname) != null;
	}

	public boolean checkExistsByEmail(String email) {

		return userDao.findByEmail(email) != null;
	}

	public boolean checkExistsByNicknameAndIdNot(String nickname, long id) {

		return userDao.findByNicknameAndIdNot(nickname, id) != null;
	}

	public boolean checkExistsByEmailAndIdNot(String email, long id) {

		return userDao.findByEmailAndIdNot(email, id) != null;
	}

	public boolean checkExistsByGoogleID(String googleAuthID) {

		return userDao.findByGoogleID(googleAuthID) != null;
	}

	public DataTablesOutput<User> findAllActiveUsers(DataTablesInput input) {
		Specification<User> spec = new Specification<User>() {

			public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate pred = cb.isFalse(root.get(User_.deletedAccount));
				return pred;
			}
		};
		return userDao.findAll(input, spec);
	}

	public List<User> findAllActiveUsers() {
		return userDao.findByDeletedAccount(false);
	}

	@PreAuthorize("hasRole('ROLE_SUPERADMIN')")
	public DataTablesOutput<User> findAllDeletedUsers(DataTablesInput input) {
		Specification<User> spec = new Specification<User>() {

			public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate pred = cb.isTrue(root.get(User_.deletedAccount));
				return pred;
			}
		};
		return userDao.findAll(input, spec);
	}

	public User findByActivateLink(String activateLink) {

		return userDao.findByActivateLinkAndDeletedAccount(activateLink, false);
	}

	public User findByEmail(String email) {

		return userDao.findByEmailAndDeletedAccount(email, false);
	}

	public User findByForgetPassLink(String rpCode) {
		return userDao.findByForgetPassLinkAndDeletedAccount(rpCode, false);
	}

	public User findById(long userId) {
		return userDao.findByIdAndDeletedAccount(userId, false);
	}

	public User findByNickname(String nickname) {
		return userDao.findByNicknameAndDeletedAccount(nickname, false);
	}

	public User registerNewUserFromDto(UserDto userDTO) {

		User user = getUserFromDtoObject(userDTO);
		Role role = roleService.findByName("ROLE_USER");
		if (role != null) {
			user.addRole(role);
		}
		user = saveUser(user);
		sendActivationLink(user);

		return user;
	}

	@PreAuthorize("(!@userDao.findById(#userDto.getId()).isAdmin() or hasRole('ROLE_SUPERADMIN')) or (@userDao.findById(#userDto.getId()).getNickname() == authentication.name)")
	public User changeUserFromDtoWithoutPassword(UserDto userDto) {

		User user = findById(userDto.getId());
		user.setFirstname(userDto.getFirstname());
		user.setLastname(userDto.getLastname());
		user.setNickname(userDto.getNickname());
		user.setEmail(userDto.getEmail());
		user.setBirthdate(userDto.getBirthdate());
		user.setCountry(userDto.getCountry());
		user.setCity(userDto.getCity());
		user.setPhonenumber(userDto.getPhonenumber().equals("") ? 0 : Integer.parseInt(userDto.getPhonenumber()));

		user = saveUser(user);

		return user;
	}

	@PreAuthorize("(!@userDao.findById(#userDto.getId()).isAdmin() or hasRole('ROLE_SUPERADMIN')) or (@userDao.findById(#userDto.getId()).getNickname() == authentication.name)")
	public User changePasswordFromDto(UserDto userDto) {
		User user = findById(userDto.getId());
		user.setPassword(passwordEncoder.encode(userDto.getPassword()));
		saveUser(user);

		return user;
	}

	public User getUserFromDtoObject(UserDto userDto) {

		User user = new User();
		user.setFirstname(userDto.getFirstname());
		user.setLastname(userDto.getLastname());
		user.setNickname(userDto.getNickname());
		user.setEmail(userDto.getEmail());
		user.setPassword(passwordEncoder.encode(userDto.getPassword()));
		user.setBirthdate(userDto.getBirthdate());
		user.setCountry(userDto.getCountry());
		user.setCity(userDto.getCity());
		user.setPhonenumber(userDto.getPhonenumber().equals("") ? 0 : Integer.parseInt(userDto.getPhonenumber()));

		return user;
	}

	public UserDto getUserDtoByUserId(long userId) {
		User user = findById(userId);

		UserDto userDto = new UserDto();
		userDto.setBirthdate(user.getBirthdate());
		userDto.setCity(user.getCity());
		userDto.setCountry(user.getCountry());
		userDto.setEmail(user.getEmail());
		userDto.setFirstname(user.getFirstname());
		userDto.setId(user.getId());
		userDto.setLastname(user.getLastname());
		userDto.setNickname(user.getNickname());
		String phoneNumStr = Integer.toString(user.getPhonenumber());
		userDto.setPhonenumber(phoneNumStr.equals("0") ? "" : phoneNumStr);

		return userDto;
	}

	public boolean activateAccountById(Long id) {
		User user = userDao.findByIdAndDeletedAccount(id, false);
		if (user == null) {
			return false;
		}
		user.setActivateDate(new Date());
		user.setActivateLink(null);
		saveUser(user);

		return true;
	}

	@PreAuthorize("(!@userDao.findById(#userId).isAdmin() or hasRole('ROLE_SUPERADMIN')) and (@userDao.findById(#userId).getNickname() != authentication.name)")
	public boolean deleteAccount(Long userId, String username) {
		User user = userDao.findByIdAndDeletedAccount(userId, false);
		if (user == null) {
			return false;
		}

		user.setDeletedAccount(true);
		user.setDeletedDate(new Date());
		user.setDeletedBy(username);
		saveUser(user);

		return true;
	}

	@PreAuthorize("hasRole('ROLE_SUPERADMIN')")
	public boolean restoreAccount(Long userId) {
		User user = userDao.findByIdAndDeletedAccount(userId, true);
		if (user == null) {
			return false;
		}

		user.setDeletedAccount(false);
		user.setDeletedDate(null);
		user.setDeletedBy(null);
		saveUser(user);

		return true;
	}

	public boolean sendActivationLink(User user) {

		if (user.getActivateLink() == null) {
			return false;
		}

		StringBuilder message = new StringBuilder();
		message.append("Aktywacja konta ");
		message.append("http://localhost:8080/ServiceDesk/activate/" + user.getActivateLink());
		emailService.sendEmail(user.getEmail(), "Link aktywacyjny", message.toString());
		return true;
	}

	public boolean sendActivationLink(Long userId) {
		User user = userDao.findByIdAndDeletedAccount(userId, false);
		return sendActivationLink(user);
	}

	public boolean sendPassLinkById(Long userId) {
		User user = userDao.findByIdAndDeletedAccount(userId, false);
		if (user == null) {
			return false;
		}

		remindPassService.generateLinkAndSendEmail(user);
		return true;
	}

	public User saveUser(User user) {

		return userDao.save(user);
	}

	public void changeUserRoleById(long userId, int roleId) {
		User user = findById(userId);

		user.clearRole();

		Role role;
		for (int i = 1; i < roleId + 1; i++) {
			role = roleService.findById(i);
			if (role != null) {
				user.addRole(role);
			}
		}

		saveUser(user);
	}

	public void logSuccessLogin(String username, String ipAddress) {
		Log log = new Log();
		log.setUsername(username);
		log.setDate(new Date());
		log.setLogType(LogTypes.SUCCESS_LOGIN);
		log.setIpAddress(ipAddress);
		logService.saveLog(log);

	}

	public void logRememberMeLogin(String name, String remoteAddr) {
		Log log = new Log();
		log.setUsername(name);
		log.setDate(new Date());
		log.setLogType(LogTypes.REMEMBER_ME_LOGIN);
		log.setIpAddress(remoteAddr);
		logService.saveLog(log);
	}

	public Map<Long, String> getMapIdUser() {

		List<User> usersList = findAllActiveUsers();
		Map<Long, String> users = new HashMap<Long, String>();

		for (User user : usersList) {
			users.put(user.getId(), user.getNickname());
		}

		return sortByValue(users);
	}

	public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
		List<Map.Entry<K, V>> list = new LinkedList<Entry<K, V>>(map.entrySet());
		Collections.sort(list, new Comparator<Map.Entry<K, V>>() {
			public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2) {
				return (o1.getValue()).compareTo(o2.getValue());
			}
		});

		Map<K, V> result = new LinkedHashMap<K, V>();
		for (Map.Entry<K, V> entry : list) {
			result.put(entry.getKey(), entry.getValue());
		}
		return result;
	}

	public User registerNewUserFromGoogle(UserGoogle userGoogle) {

		User user = new User(userGoogle.getGoogleAuthID(), userGoogle.getGoogleAuthEmail(),
				userGoogle.getGoogleAuthFirstName(), userGoogle.getGoogleAuthLastName());
		Role role = roleService.findByName("ROLE_USER");
		if (role != null) {
			user.addRole(role);
		}
		String googlePassword = UUID.randomUUID().toString();
		user.setGooglePassword(googlePassword);
		user.setGoogleHashedPassword(passwordEncoder.encode(googlePassword));
		user.setPassword(passwordEncoder.encode(googlePassword));
		user = saveUser(user);

		URL url;
		try {
			url = new URL(userGoogle.getGoogleAuthImageUrl());
			// read image direct from url
			BufferedImage image = ImageIO.read(url);

			// write image to outputstream
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(image, "png", baos);
			baos.flush();

			// get bytes
			byte[] imageBytes = baos.toByteArray();

			UploadFile uploadFile = new UploadFile();
			uploadFile.setFileName(user.getNickname().concat("-image"));
			uploadFile.setData(imageBytes);
			uploadFile.setUsername(user.getNickname());
			fileUploadService.save(uploadFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return user;

	}

	public User findByGoogleID(String googleAuthID) {

		return userDao.findByGoogleID(googleAuthID);
	}

	public User connectAccountWithGoogle(UserGoogle userGoogle) {
		User user = findByEmail(userGoogle.getGoogleAuthEmail());
		if (user != null) {
			user.setgoogleID(userGoogle.getGoogleAuthID());
			if (user.getFirstname() == null || user.getFirstname().equals("")) {
				user.setFirstname(userGoogle.getGoogleAuthFirstName());
			}
			if (user.getLastname() == null || user.getLastname().equals("")) {
				user.setLastname(userGoogle.getGoogleAuthLastName());
			}
			String googlePassword = UUID.randomUUID().toString();
			user.setGooglePassword(googlePassword);
			user.setGoogleHashedPassword(passwordEncoder.encode(googlePassword));
		} else {
			try {
				throw new Exception("User with this email not exists!");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return saveUser(user);
	}

}
