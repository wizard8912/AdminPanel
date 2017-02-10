package pl.pniedziela.uploader;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.pniedziela.user.UserService;

@Service
public class FileUploadService {

	@Autowired
	FileUploadDAO fileUploadDao;
	@Autowired
	UserService userService;

	@Transactional
	public void save(UploadFile uploadFile) {
		deleteOldUserAvatars(uploadFile.getUsername());
		fileUploadDao.save(uploadFile);
	}

	private void deleteOldUserAvatars(String username) {
		fileUploadDao.deleteByUsername(username);
	}

	public byte[] getAvatarForUsername(String username) {
		List<UploadFile> avatars = fileUploadDao.findByUsername(username);
		if (avatars.size() > 0) {
			return avatars.get(0).getData();
		}
		return getDefaultAvatar();
	}

	private byte[] getDefaultAvatar() {
		List<UploadFile> avatars = fileUploadDao.findByUsername("defaultAvatar");
		if (avatars.size() > 0) {
			return avatars.get(0).getData();
		}
		return null;
	}

	public String getUsernameById(long userId) {

		return userService.findById(userId).getNickname();
	}

}
