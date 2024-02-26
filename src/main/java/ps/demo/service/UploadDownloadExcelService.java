package ps.demo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ps.demo.common.MapperTool;
import ps.demo.dto.UploadDownloadExcelDto;
import ps.demo.entity.UploadDownloadExcel;
import ps.demo.repository.UploadDownloadMapper;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class UploadDownloadExcelService {

    @Autowired
    private UploadDownloadMapper uploadDownloadMapper;

    @Transactional
    public List<Integer> saveExcelDtoList(List<UploadDownloadExcelDto> list) {
        List<Integer> result = new ArrayList();
        for (UploadDownloadExcelDto dto : list) {
            //MapperTool
            UploadDownloadExcel uploadDownloadExcel = new UploadDownloadExcel();
            BeanUtils.copyProperties(dto, uploadDownloadExcel);
            result.add(uploadDownloadMapper.insert(uploadDownloadExcel));
        }
        return result;
    }

}
