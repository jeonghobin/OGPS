package com.ssafy.enjoy.board.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.enjoy.board.dto.FileInfoDto;
import com.ssafy.enjoy.board.dto.ReviewComDto;
import com.ssafy.enjoy.board.dto.ReviewDto;
import com.ssafy.enjoy.board.dto.ReviewHeartDto;
import com.ssafy.enjoy.board.service.ReviewBoardService;

@RestController
@RequestMapping(value = "/api")
public class RestReviewboardController {

	@Autowired
	@Qualifier("ReviewBoardServiceMapperImpl")
	ReviewBoardService service;

	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	private final Logger logger = LoggerFactory.getLogger(RestReviewboardController.class);
	
	@Autowired
	private ServletContext servletContext;
	
	
	/**
	 * 게시판
	 **/
	@GetMapping("/review")
	public ResponseEntity<Map<String, Object>> list(@RequestParam Map<String, String> reqmap) {
		Map<String, Object> map = new HashMap<String, Object>();
		HttpStatus status = null;

		try {
			List<ReviewDto> list = service.list(reqmap);

			map.put("data", list);
			map.put("pgno", reqmap.get("pgno"));
			map.put("key", reqmap.get("key"));
			map.put("word", reqmap.get("word"));
			map.put("message", SUCCESS);
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			map.put("message", FAIL);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			e.printStackTrace();
		}

		return new ResponseEntity<Map<String, Object>>(map, status);
	}

	@PostMapping("/review")
	public ResponseEntity<Map<String, Object>> write(ReviewDto rdto) {
		Map<String, Object> map = new HashMap<String, Object>();
		HttpStatus status = null;

		try {
			int articleNo = service.write(rdto);
			
			map.put("message", SUCCESS);
			map.put("articleNo", articleNo);
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			map.put("message", FAIL);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			e.printStackTrace();
		}

		return new ResponseEntity<Map<String, Object>>(map, status);
	}

	@DeleteMapping("/review/{articleNo}")
	public ResponseEntity<Map<String, Object>> delete(@PathVariable(value = "articleNo") int articleNo) {
		Map<String, Object> map = new HashMap<String, Object>();
		HttpStatus status = null;

		try {
			service.delete(articleNo);

			map.put("message", SUCCESS);
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			map.put("message", FAIL);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			e.printStackTrace();
		}

		return new ResponseEntity<Map<String, Object>>(map, status);
	}

	@PutMapping("/review")
	public ResponseEntity<Map<String, Object>> modify(@RequestBody Map<String, String> reqmap) {
		Map<String, Object> map = new HashMap<String, Object>();
		HttpStatus status = null;

		try {
			ReviewDto rdto = new ReviewDto();
			rdto.setUserId(reqmap.get("userId"));
			rdto.setSubject(reqmap.get("subject"));
			rdto.setContent(reqmap.get("content"));

			service.modify(rdto); 

			map.put("message", SUCCESS);
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			map.put("message", FAIL);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			e.printStackTrace();
		}

		return new ResponseEntity<Map<String, Object>>(map, status);
	}

	/**
	 * 댓글
	 **/
	@GetMapping("/review/{articleNo}")
	public void view(HttpServletResponse response, @PathVariable("articleNo") int articleNo) {
		Map<String, Object> map = new HashMap<String, Object>();
		HttpStatus status = null;

		try {
			FileInfoDto[] files = service.getFile(articleNo);
			for (FileInfoDto fIDto : files) {
				String realPath = servletContext.getRealPath("/upload");
				
				// db에서 폴더명 조회 
				String folderName = fIDto.getSaveFolder();
				String imageName = fIDto.getSaveFile();
				String imagePath = realPath + File.separator + folderName  + File.separator + imageName;
//				File imagefile = new File(imagePath);
				
			    byte[] fileByte = FileUtils.readFileToByteArray(new File(imagePath));
			    response.setContentType("application/octet-stream");
			    response.setHeader("Content-Disposition", "attachment; fileName=\"" + URLEncoder.encode("tistory.png", "UTF-8")+"\";");
			    response.setHeader("Content-Transfer-Encoding", "binary");

			    response.getOutputStream().write(fileByte);
			    response.getOutputStream().flush();
			    response.getOutputStream().close();
			}
			
//			ReviewDto rdto = service.getnotice(articleNo);
//			List<ReviewComDto> rcom = service.comList(articleNo);
//			
//			service.allHeart(rdto.getArticleNo());
//
//			map.put("review", rdto);
//			map.put("comment", rcom);
//			map.put("csize", rcom.size());
//			map.put("message", SUCCESS);
//			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
//			map.put("message", FAIL);
//			status = HttpStatus.INTERNAL_SERVER_ERROR;
//			e.printStackTrace();
		}
//		return new ResponseEntity<Map<String, Object>>(map, status);
	}
	
	
	@PostMapping("/rcomment")
	public ResponseEntity<Map<String, Object>> comwrite(@RequestBody Map<String, String> reqmap) {
		Map<String, Object> map = new HashMap<String, Object>();
		HttpStatus status = null;

		try {
			ReviewComDto fcdto = new ReviewComDto();
			fcdto.setArticleNo(Integer.parseInt(reqmap.get("articleNo")));
			fcdto.setComment(reqmap.get("comment").replace("\r\n", "<br>"));
			fcdto.setUserId(reqmap.get("userId"));

			service.comWrite(fcdto);

			map.put("message", SUCCESS);
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			map.put("message", FAIL);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			e.printStackTrace();
		}

		return new ResponseEntity<Map<String, Object>>(map, status);
	}

	@DeleteMapping("/rcomment/{commentNo}")
	public ResponseEntity<Map<String, Object>> comdelete(@PathVariable("commentNo") int commentNo) {
		Map<String, Object> map = new HashMap<String, Object>();
		HttpStatus status = null;

		try {
			service.comDelete(commentNo);

			map.put("message", SUCCESS);
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			map.put("message", FAIL);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			e.printStackTrace();
		}

		return new ResponseEntity<Map<String, Object>>(map, status);
	}

	/**
	 * 좋아요
	 **/
	@PostMapping("/rheart")
	public ResponseEntity<Map<String, Object>> heartUpdate(@RequestBody ReviewHeartDto rhDto) {
		Map<String, Object> map = new HashMap<String, Object>();
		HttpStatus status = null;

		try {
			int exitHeart = service.exitHeart(rhDto);

			if (exitHeart > 0) {
				service.updateHeart(rhDto);
			} else {
				service.addHeart(rhDto);
			}

			map.put("message", SUCCESS);
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			map.put("message", FAIL);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			e.printStackTrace();
		}

		return new ResponseEntity<Map<String, Object>>(map, status);
	}

	/**
	 * 파일
	 **/
	@PostMapping("/rfile")
	public ResponseEntity<Map<String, Object>> filesave(@RequestParam("upfile") MultipartFile[] files, int articleNo) {
		Map<String, Object> map = new HashMap<String, Object>();
		HttpStatus status = null;

		try {
//			FileUpload 관련 설정.
			logger.debug("MultipartFile.isEmpty : {}", files[0].isEmpty());
			if (!files[0].isEmpty()) {
				String realPath = servletContext.getRealPath("/upload");
				String today = new SimpleDateFormat("yyMMdd").format(new Date());
				String saveFolder = realPath + File.separator + today;
				logger.debug("저장 폴더 : {}", saveFolder);
				File folder = new File(saveFolder);
				if (!folder.exists())
					folder.mkdirs();
				List<FileInfoDto> fileInfos = new ArrayList<FileInfoDto>();
				for (MultipartFile mfile : files) {
					FileInfoDto fileInfoDto = new FileInfoDto();
					fileInfoDto.setArticleNo(articleNo);
					String originalFileName = mfile.getOriginalFilename();
					if (!originalFileName.isEmpty()) {
						String saveFileName = UUID.randomUUID().toString()
								+ originalFileName.substring(originalFileName.lastIndexOf('.'));
						fileInfoDto.setSaveFolder(today);
						fileInfoDto.setOriginalFile(originalFileName);
						fileInfoDto.setSaveFile(saveFileName);
						logger.debug("원본 파일 이름 : {}, 실제 저장 파일 이름 : {}", mfile.getOriginalFilename(), saveFileName);
						mfile.transferTo(new File(folder, saveFileName));
					}
					fileInfos.add(fileInfoDto);
					service.savefile(fileInfoDto);
				}
				map.put("imageUrl", fileInfos);
			}

			
			map.put("message", SUCCESS);
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			map.put("message", FAIL);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			e.printStackTrace();
		}

		return new ResponseEntity<Map<String, Object>>(map, status);
	}
	
	@DeleteMapping("/rfile/{idx}")
	public ResponseEntity<Map<String, Object>> filedelete(@PathVariable("idx") int idx) {
		Map<String, Object> map = new HashMap<String, Object>();
		HttpStatus status = null;

		try {
			service.deletefile(idx);

			map.put("message", SUCCESS);
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			map.put("message", FAIL);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			e.printStackTrace();
		}

		return new ResponseEntity<Map<String, Object>>(map, status);
	}
	
	
}
