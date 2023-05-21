package it.indra.SigecAPI.service;

import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Base64;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.sanselan.Sanselan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.drew.imaging.ImageMetadataReader;
import com.drew.metadata.exif.ExifIFD0Directory;
import com.drew.metadata.jpeg.JpegDirectory;
import com.indra.es.utils.LogUtility;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class FileRicevutaService {

	@Value("${filesystem.repository.image}")
	String fileStorageLocation;

	Path fileStorageLocationPath;

	@Autowired
	ServletContext servletContext;

	public String reduceFile(String path, String newPath, String tmpPath) {

		log.info("ENTRATO IN STORE FILE");
		try {
//			String path = fileStorageLocation +System.getProperty("file.separator") + "users" + System.getProperty("file.separator")  + idUtente;

			File fileOriginal = new File(path);

			FileInputStream input = new FileInputStream(fileOriginal);
			MultipartFile file = new MockMultipartFile("file", fileOriginal.getName(), "text/plain",
					IOUtils.toByteArray(input));

			log.info("FILEPATH " + path);
			UUID uuid = UUID.randomUUID();

			fileStorageLocationPath = Paths.get(path, "");

//			Files.createDirectories(fileStorageLocationPath);
//			String fileName =uuid.toString();// StringUtils.cleanPath(file.getOriginalFilename());

//			Path targetLocation = fileStorageLocationPath.resolve(fileName);
//			Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

			int targetWidth;
			int targetHeight;
			File fileTmp = new File(tmpPath);

			try (OutputStream os = new FileOutputStream(fileTmp)) {
				os.write(file.getBytes());
			}

			String name = file.getOriginalFilename();
			String mimeType = servletContext.getMimeType(name);
			if (mimeType != null && mimeType.startsWith("image/") && !mimeType.toUpperCase().contains("TIFF")) {

				BufferedImage bimg = ImageIO.read(fileTmp);

				String extension = FilenameUtils.getExtension(file.getOriginalFilename());

				int width = bimg.getWidth();
				int height = bimg.getHeight();

				BufferedImage bifRes = null;
				if (width > 400 || height > 400) {
					switch (extension) {
					case "jpg":
					case "jpeg":
					case "JPG":

						bifRes = getJpgImage(bimg, fileTmp);

						break;
					case "png":
					case "PNG":

						bifRes = getPngImage(bimg, fileTmp);

						break;
					default:
						bifRes = getImage(bimg, fileTmp);
					}

				} else {
					bifRes = bimg;
				}

				ImageIO.write(bifRes, extension, new File(newPath));

			} else {
				File fileNew = new File(fileStorageLocationPath + System.getProperty("file.separator") + newPath);
				fileTmp.renameTo(fileNew);
			}

			fileTmp.delete();
			log.info("FILENAME TROVATO " + newPath);
			return newPath;
		} catch (Exception ex) {
			log.error(LogUtility.exceptionToLog(ex));
			return "";
		}

	}

	private BufferedImage getPngImage(BufferedImage bimg, File fileTmp) {
		try {

			int width = bimg.getWidth();
			int height = bimg.getHeight();

			int targetWidth;
			int targetHeight;

			float proportion;
			BufferedImage bifRes = Sanselan.getBufferedImage(fileTmp);

			if (width > 400 || height > 400) {
				if (width > height) {
					proportion = (float) width / (float) height;
					targetWidth = 400;
					targetHeight = (int) ((float) targetWidth / proportion);

				} else {
					proportion = (float) height / (float) width;
					targetHeight = 400;
					targetWidth = (int) ((float) targetHeight / proportion);

				}

				bifRes = resizeImage(bimg, targetWidth, targetHeight);

			}

			return bifRes;
		} catch (Exception e) {
			log.error(LogUtility.exceptionToLog(e));
		}
		return null;
	}

	private BufferedImage getImage(BufferedImage bimg, File fileTmp) {
		try {

			int width = bimg.getWidth();
			int height = bimg.getHeight();

			int targetWidth;
			int targetHeight;

			float proportion;
			BufferedImage bifRes = ImageIO.read(fileTmp);

			if (width > 400 || height > 400) {
				if (width > height) {
					proportion = (float) width / (float) height;
					targetWidth = 400;
					targetHeight = (int) ((float) targetWidth / proportion);

				} else {
					proportion = (float) height / (float) width;
					targetHeight = 400;
					targetWidth = (int) ((float) targetHeight / proportion);

				}

				bifRes = resizeImage(bimg, targetWidth, targetHeight);

			}

			return bifRes;
		} catch (Exception e) {
			log.error(LogUtility.exceptionToLog(e));
		}
		return null;
	}

	BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) throws IOException {
		Image resultingImage = originalImage.getScaledInstance(targetWidth, targetHeight, Image.SCALE_DEFAULT);
		BufferedImage outputImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
		outputImage.getGraphics().drawImage(resultingImage, 0, 0, null);
		return outputImage;
	}

	public String loadFileAsByte64(String path, String estensione, String filename) {
		try {
			fileStorageLocationPath = Paths.get(fileStorageLocation, "");

			if (path != null) {

				fileStorageLocationPath = Paths.get(path, "");

				String filePosition = null;

				switch (estensione) {
				case "jpg":
				case "JPG":
				case "PNG":
				case "jpeg":
				case "JPEG":
				case "png":

					filePosition = path;
					break;
				default:
					filePosition = path;
					break;
				}

				log.info("DOWNLOAD file  IN " + filePosition);

				Path filePath = fileStorageLocationPath.resolve(filePosition).normalize();
				Resource resource = new UrlResource(filePath.toUri());

//				String name = file.getNome();
				String mimeType = servletContext.getMimeType(filename);
				byte[] fileContent = Files.readAllBytes(filePath);
				String base64 = Base64.getEncoder().encodeToString(fileContent);
				String response = "data:" + mimeType + ";base64," + base64;

				return response;
			} else {

//				log.error("FILE NON TROVATO CON ID " + idFile);
				return null;
			}
		} catch (Exception ex) {
			log.error(LogUtility.exceptionToLog(ex));
			// throw new MyFileNotFoundException("File not found " + fileName, ex);
		}
		return null;
	}

	private BufferedImage getJpgImage(BufferedImage bimg, File fileTmp) {
		try {
			com.drew.metadata.Metadata metadata = ImageMetadataReader.readMetadata(fileTmp);
			ExifIFD0Directory exifIFD0Directory = metadata.getDirectory(ExifIFD0Directory.class);
			JpegDirectory jpegDirectory = (JpegDirectory) metadata.getDirectory(JpegDirectory.class);

			int targetWidth;
			int targetHeight;
			int orientation = 1;
			try {
				orientation = exifIFD0Directory.getInt(ExifIFD0Directory.TAG_ORIENTATION);
			} catch (Exception ex) {
				orientation = 1;
			}

			int width = jpegDirectory.getImageWidth();
			int height = jpegDirectory.getImageHeight();
			AffineTransform affineTransform = new AffineTransform();

			switch (orientation) {
			case 1:
				break;
			case 2: // Flip X
				affineTransform.scale(-1.0, 1.0);
				affineTransform.translate(-width, 0);
				break;
			case 3: // PI rotation
				affineTransform.translate(width, height);
				affineTransform.rotate(Math.PI);
				break;
			case 4: // Flip Y
				affineTransform.scale(1.0, -1.0);
				affineTransform.translate(0, -height);
				break;
			case 5: // - PI/2 and Flip X
				affineTransform.rotate(-Math.PI / 2);
				affineTransform.scale(-1.0, 1.0);
				break;
			case 6: // -PI/2 and -width
				affineTransform.translate(height, 0);
				affineTransform.rotate(Math.PI / 2);
				break;
			case 7: // PI/2 and Flip
				affineTransform.scale(-1.0, 1.0);
				affineTransform.translate(-height, 0);
				affineTransform.translate(0, width);
				affineTransform.rotate(3 * Math.PI / 2);
				break;
			case 8: // PI / 2
				affineTransform.translate(0, width);
				affineTransform.rotate(3 * Math.PI / 2);
				break;
			default:
				break;
			}

			AffineTransformOp affineTransformOp = new AffineTransformOp(affineTransform,
					AffineTransformOp.TYPE_BILINEAR);
			bimg = affineTransformOp.filter(bimg, null);

			width = bimg.getWidth();
			height = bimg.getHeight();

			float proportion;
			BufferedImage bifRes;
			if (width > 400 || height > 400) {
				if (width > height) {
					proportion = (float) width / (float) height;
					targetWidth = 400;
					targetHeight = (int) ((float) targetWidth / proportion);

				} else {
					proportion = (float) height / (float) width;
					targetHeight = 400;
					targetWidth = (int) ((float) targetHeight / proportion);

				}

				bifRes = resizeImage(bimg, targetWidth, targetHeight);

			} else {
				bifRes = bimg;
				targetHeight = height;
				targetWidth = width;
			}

			return bifRes;
		} catch (Exception e) {
			log.error(LogUtility.exceptionToLog(e));
		}
		return null;
	}

	public void storePdf(byte[] data, String path, Long eventoId) {

		File dir = new File(path);
		try {

			dir.mkdirs();
	
	File file = new File (path +  File.separator + "ricevuta.pdf");	

		OutputStream os = new FileOutputStream(file);
			os.write(data);
			os.close();
		} catch (Exception e) {
			log.error("IMPOSSIBILE GENERARE RICEVUTA");
			log.error(LogUtility.exceptionToLog(e));

		}

	}

	public boolean checkFile(Long idEvento, String path) 
	{
		File file = new File (path +  File.separator + "ricevuta.pdf");
		return file.exists();
	}
}
