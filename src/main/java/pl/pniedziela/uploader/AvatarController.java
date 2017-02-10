package pl.pniedziela.uploader;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

@Controller
@RequestMapping("/user/avatars")
public class AvatarController {

	@Autowired
	FileUploadService fileUploadService;

	@RequestMapping("/{username}")
	@ResponseBody
	public byte[] getAvatarForUser(@PathVariable String username) {
		byte[] image = fileUploadService.getAvatarForUsername(username);
		return image;
	}

	@PreAuthorize("#userId == principal.id || hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/{userId}/doUpload", method = RequestMethod.POST)
	public String handleFileUpload(HttpServletRequest request, @RequestParam CommonsMultipartFile[] fileUpload,
			@PathVariable long userId) throws Exception {

		if (fileUpload != null && fileUpload.length > 0) {
			for (CommonsMultipartFile aFile : fileUpload) {

				UploadFile uploadFile = new UploadFile();
				uploadFile.setFileName(aFile.getOriginalFilename());
				uploadFile.setData(aFile.getBytes());
				uploadFile.setUsername(fileUploadService.getUsernameById(userId));
				fileUploadService.save(uploadFile);
			}
		}

		return "redirect:/user/account/" + userId;
	}
}
