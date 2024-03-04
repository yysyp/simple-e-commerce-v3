



package ps.demo.mytftemplate.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import ps.demo.common.BaseController;
import ps.demo.common.BasePageResData;
import ps.demo.common.MapperTool;
import ps.demo.mytftemplate.dto.AbcStaffDto;
import ps.demo.mytftemplate.dto.AbcStaffReq;
import ps.demo.mytftemplate.service.AbcStaffServiceImpl;


@Slf4j
@RestController
@RequestMapping("/api/mytftemplate/abc-staff")
public class AbcStaffTfController extends BaseController {

    @Autowired
    private AbcStaffServiceImpl abcStaffServiceImpl;

    @GetMapping("/form")
    public ModelAndView createForm(Model model) {
        model.addAttribute("abcStaffDto", new AbcStaffDto());
        return new ModelAndView("mytftemplate/abc-staff-form", "abcStaffModel", model);
    }

    @PostMapping("/save")
    public ModelAndView save(AbcStaffReq abcStaffReq, HttpServletRequest request) {
        AbcStaffDto abcStaffDto = new AbcStaffDto();
        
            
            
            
            
            
            abcStaffDto.setPassed(null != request.getParameter("passed"));


        MapperTool.copyProperties(abcStaffReq, abcStaffDto);
        initBaseCreateModifyTs(abcStaffDto);
        AbcStaffDto abcStaffResult = abcStaffServiceImpl.save(abcStaffDto);
        return new ModelAndView("redirect:/api/mytftemplate/abc-staff");
    }

    @GetMapping("/list")
    public ModelAndView navigateToQuery() {
        return new ModelAndView("redirect:/api/mytftemplate/abc-staff");
    }

    @GetMapping
    public ModelAndView query(Model model) {
        AbcStaffReq abcStaffReq = new AbcStaffReq();
        model.addAttribute("abcStaffReq", abcStaffReq);
        Pageable pageable = constructPagable(abcStaffReq);
        Page<AbcStaffDto> abcStaffDtoPage = abcStaffServiceImpl.findInPage(pageable);
        BasePageResData<AbcStaffDto> myPageResData = new BasePageResData<>(abcStaffDtoPage,
                abcStaffReq.getCurrent(), abcStaffReq.getSize());
        model.addAttribute("abcStaffReq", abcStaffReq);
        model.addAttribute("page", myPageResData);
        return new ModelAndView("mytftemplate/abc-staff-list", "abcStaffModel", model);
    }

    @GetMapping("/{id}")
    public ModelAndView getById(@PathVariable("id") Long id, Model model) {
        AbcStaffDto abcStaffDto = abcStaffServiceImpl.findById(id);
        model.addAttribute("abcStaffDto", abcStaffDto);
        return new ModelAndView("mytftemplate/abc-staff-view", "abcStaffModel", model);
    }

    @PostMapping("/list")
    public ModelAndView list(Model model, AbcStaffReq abcStaffReq) {
        Pageable pageable = constructPagable(abcStaffReq);
        AbcStaffDto abcStaffDto = new AbcStaffDto();
        String key = abcStaffReq.getKey();
        if (StringUtils.isNotBlank(key)) {
            String percentWrapKey = "%" + key + "%";
            
                
                abcStaffDto.setFirstName(percentWrapKey);
                
                
                abcStaffDto.setLastName(percentWrapKey);
                
                
                
                
                
                abcStaffDto.setComments(percentWrapKey);
                
                
            
        }
        //MyBeanUtil.copyProperties(abcStaffReq, abcStaffDto);
        Page<AbcStaffDto> abcStaffDtoPage = abcStaffServiceImpl.findByAttributesInPage(abcStaffDto, true, pageable);
        BasePageResData<AbcStaffDto> myPageResData = new BasePageResData<>(abcStaffDtoPage,
                abcStaffReq.getCurrent(), abcStaffReq.getSize());
        model.addAttribute("abcStaffReq", abcStaffReq);
        model.addAttribute("page", myPageResData);
        return new ModelAndView("mytftemplate/abc-staff-list", "abcStaffModel", model);
    }

    @GetMapping("/modify/{id}")
    public ModelAndView modify(@PathVariable("id") Long id, Model model) {
        AbcStaffDto abcStaffDto = abcStaffServiceImpl.findById(id);
        model.addAttribute("abcStaffDto", abcStaffDto);
        return new ModelAndView("mytftemplate/abc-staff-modify", "abcStaffModel", model);
    }

    @PostMapping("/modify")
    public ModelAndView saveOrUpdate(AbcStaffDto abcStaffDto, HttpServletRequest request) {
        initBaseCreateModifyTs(abcStaffDto);
        
            
            
            
            
            
            abcStaffDto.setPassed(null != request.getParameter("passed"));
            
            
            
        
        AbcStaffDto updatedAbcStaffDto = abcStaffServiceImpl.save(abcStaffDto);
        return new ModelAndView("redirect:/api/mytftemplate/abc-staff");
    }

    @GetMapping("/remove/{id}")
    public ModelAndView remove(@PathVariable("id") Long id) {
        abcStaffServiceImpl.deleteById(id);
        return new ModelAndView("redirect:/api/mytftemplate/abc-staff");
    }

}




