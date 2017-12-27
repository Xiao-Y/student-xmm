package org.billow.utils.image;

import org.billow.utils.RequestUtils;
import org.billow.utils.ToolsUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * 图片工具类
 *
 * @author liuyongtao
 * @create 2017-11-17 16:59
 */
@Service
public class ImageUtils {

    //商品图片路径
    private static String upload;
    private static String systemDomainName;
    private static String defaultImg;

    @Value("${commodity.img.upload}")
    public void setUpload(String uploadTemp) {
        ImageUtils.upload = uploadTemp;
    }

    @Value("${system.domain.name}")
    public void setSystemDomainName(String systemDomainNameTemp) {
        ImageUtils.systemDomainName = systemDomainNameTemp;
    }

    @Value("${commodity.img.default}")
    public void setDefaultImg(String defaultImg) {
        ImageUtils.defaultImg = defaultImg;
    }

    /**
     * 将网络图片进行Base64位编码<br/>
     * 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
     *
     * @param imageUrl 图片的url路径，如http://.....xx.png
     * @return
     */
    public static String encodeImgageToBase64(URL imageUrl) {
        ByteArrayOutputStream outputStream = null;
        try {
            BufferedImage bufferedImage = ImageIO.read(imageUrl);
            outputStream = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, "png", outputStream);
        } catch (MalformedURLException e1) {
            e1.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();
        // 返回Base64编码过的字节数组字符串
        return encoder.encode(outputStream.toByteArray());
    }

    /**
     * 将本地图片进行Base64位编码<br/>
     * 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
     *
     * @param imageFile 图片
     * @return
     */
    public static String encodeImgageToBase64(File imageFile) {
        ByteArrayOutputStream outputStream = null;
        try {
            BufferedImage bufferedImage = ImageIO.read(imageFile);
            outputStream = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, "png", outputStream);
        } catch (MalformedURLException e1) {
            e1.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();
        // 返回Base64编码过的字节数组字符串
        return encoder.encode(outputStream.toByteArray());
    }

    /**
     * 将Base64位编码的图片进行解码，并保存到指定目录
     *
     * @param base64  base64编码的图片信息
     * @param path    保存到指定目录
     * @param imgName 图片名
     * @return
     */
    public static void decodeBase64ToImage(String base64, String path, String imgName) throws Exception {
        BASE64Decoder decoder = new BASE64Decoder();
        FileOutputStream write = new FileOutputStream(new File(path, imgName));
        byte[] decoderBytes = decoder.decodeBuffer(base64);
        write.write(decoderBytes);
        write.close();
    }

    /**
     * 删除指定的文件
     *
     * @param path
     * @param imgName
     * @throws Exception
     */
    public static void deleteImage(String path, String imgName) throws Exception {
        File file = new File(path, imgName);
        if (file.exists()) {
            file.delete();
        }
    }

    public static String getImgPath(String img) {
        String contextPath = RequestUtils.getRequest().getSession().getServletContext().getContextPath();
        if (ToolsUtils.isEmpty(img)) {
            img = defaultImg;
        }
        img = systemDomainName + contextPath + "/" + upload + "/" + img;
        return img;
    }
}