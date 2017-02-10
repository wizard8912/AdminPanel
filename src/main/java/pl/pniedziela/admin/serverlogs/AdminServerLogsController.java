package pl.pniedziela.admin.serverlogs;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class AdminServerLogsController {

	@RequestMapping("/serverlogs")
	public String getServerLogsPage(Model model) {

		List<FileInfo> filesDetails = new ArrayList<FileInfo>();

		File folder = new File(System.getProperty("catalina.base") + "/logs");
		File[] listOfFiles = folder.listFiles();
		File file;
		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
				file = listOfFiles[i];

				FileInfo fi = new FileInfo(file.lastModified(), file.getName(), file.length());
				filesDetails.add(fi);
			} else if (listOfFiles[i].isDirectory()) {
				System.out.println("Directory " + listOfFiles[i].getName());
			}
		}

		sortFiles(filesDetails);
		model.addAttribute("filesDetails", filesDetails);
		return "admin.serverlogs";
	}

	private void sortFiles(List<FileInfo> filesDetails) {

		if (filesDetails.size() > 0) {
			Collections.sort(filesDetails, new Comparator<FileInfo>() {

				public int compare(final FileInfo object1, final FileInfo object2) {
					if (object1.getLastModifiedDate() < object2.getLastModifiedDate())
						return 10;
					else if (object1.getLastModifiedDate() > object2.getLastModifiedDate())
						return -10;
					else
						return 0;
				}

			});
		}
	}

	@RequestMapping("/serverlogs/{filename}/")
	public String getLogPage(Model model, @PathVariable String filename) {

		System.out.println("==========================================");
		System.out.println(filename);
		System.out.println("==========================================");
		File file = new File(System.getProperty("catalina.base") + "/logs/" + filename);
		String logContent;
		try {
			logContent = FileUtils.readFileToString(file);

			logContent = logContent.replaceAll("\n", "<br>");
			// logContent = StringEscapeUtils.escapeHtml4("Read in: " +
			// logContent);
			model.addAttribute("logContent", logContent);
		} catch (IOException e) {
			model.addAttribute("errorAlert", "admin.ServerLogs.ErrorLoad");
			e.printStackTrace();
		}

		return "admin.serverlog";
	}
}
