package cc.mrbird.system.controller;

import cc.mrbird.common.annotation.Log;
import cc.mrbird.common.controller.BaseController;
import cc.mrbird.common.domain.QueryRequest;
import cc.mrbird.common.domain.ResponseBo;
import cc.mrbird.common.util.FileUtils;
import cc.mrbird.fastdfs.FastDFSClientUtil;
import cc.mrbird.system.domain.Advertising;
import cc.mrbird.system.service.AdvertisingService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
public class AdvertisingController extends BaseController {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private FastDFSClientUtil dfsClient;

    @Autowired
    FastFileStorageClient fastFileStorageClient;

    @Value("192.168.31.252:22122")
    private String fdfsIP;

    @Autowired
    private AdvertisingService advertisingService;

    private static final String ON = "on";

       @Log("获取广告信息")
    @RequestMapping("advertising")
    @RequiresPermissions("advertising:list")
    public String index() {
        return "system/advertising/advertising";
    }

    @RequestMapping("advertising/excel")
    @ResponseBody
    public ResponseBo advertisingExcel(Advertising advertising) {
        try {
            List<Advertising> list = this.advertisingService.findAllAdvertising(advertising, null);
            return FileUtils.createExcelByPOIKit("广告表", list, Advertising.class);
        } catch (Exception e) {
            log.error("导出广告信息Excel失败", e);
            return ResponseBo.error("导出Excel失败，请联系网站管理员！");
        }
    }

    @RequestMapping("advertising/csv")
    @ResponseBody
    public ResponseBo advertisingCsv(Advertising advertising) {
        try {
            List<Advertising> list = this.advertisingService.findAllAdvertising(advertising, null);
            return FileUtils.createCsv("广告表", list, Advertising.class);
        } catch (Exception e) {
            log.error("导出广告信息Csv失败", e);
            return ResponseBo.error("导出Csv失败，请联系网站管理员！");
        }
    }

    @RequestMapping("advertising/list")
    @RequiresPermissions("advertising:list")
    @ResponseBody
    public Map<String, Object> advertisingList(QueryRequest request, Advertising advertising) {
        PageHelper.startPage(request.getPageNum(), request.getPageSize());
        List<Advertising> list = this.advertisingService.findAllAdvertising(advertising, request);
        PageInfo<Advertising> pageInfo = new PageInfo<>(list);
        return getDataTable(pageInfo);
    }

    @Log("新增广告到Fdfs ")
    @RequestMapping("advertisingFdfs/add")
    @RequiresPermissions("advertising:add")
    @ResponseBody
    public ResponseBo advertisingFdfs(Advertising advertising ,MultipartFile file) {
        try {
            String imgUrl = dfsClient.uploadFile(file);
            System.out.println("上传得IP地址为："+fdfsIP);
            String[] Ip = fdfsIP.split(":");
            String advUrl = Ip[0]+"/"+imgUrl;
            System.out.println("上传文件得浏览路径——" + advUrl);
            advertising.setAdvUrl("http://"+advUrl);
            this.advertisingService.addAdvertising(advertising);
            return ResponseBo.ok("新增FASTDFS广告成功！");
        } catch (Exception e) {
            log.error("新增FASTDFS广告失败", e);
            return ResponseBo.error("新增FASTDFS广告失败，请联系网站管理员！");
        }
    }

    @RequestMapping("advertising/openExplorer")
    @ResponseBody
    public static void openExplorer(String urls) {
        try {
            Runtime.getRuntime().exec(
                    "cmd   /c   start   "+urls+" ");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }





}
