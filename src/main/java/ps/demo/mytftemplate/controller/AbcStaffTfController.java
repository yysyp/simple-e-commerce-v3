

package ps.demo.mytftemplate.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;
import ps.demo.common.*;
import ps.demo.mytftemplate.dto.AbcStaffDto;
import ps.demo.mytftemplate.dto.AbcStaffReq;
import ps.demo.mytftemplate.service.AbcStaffServiceImpl;
import ps.demo.common.MapperTool;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import lombok.*;

import jakarta.servlet.http.HttpServletRequest;
import java.util.*;
import java.math.*;

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
        //MapperTool.copyProperties(abcStaffReq, abcStaffDto);
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


    /* ------------ PURE RESTFUL APIs SEPARATING LINE ------------ */
    @PostMapping(value = "/rf-save-or-update")
    public BaseResData saveOrUpdateRestful(@RequestBody AbcStaffDto abcStaffDto, HttpServletRequest request) {
        initBaseCreateModifyTs(abcStaffDto);
        
        abcStaffDto.setPassed(null != request.getParameter("passed"));

        AbcStaffDto updatedAbcStaffDto = abcStaffServiceImpl.save(abcStaffDto);
        return BaseResData.builder().data(updatedAbcStaffDto).build();
    }

    @Operation(summary = "To query the data by req using and equal logic for req fields",
            description = "i.e: <pre>" +
                    "{\n" +
                    "  \"current\": 1,\n" +
                    "  \"size\": 10,\n" +
                    "  \"orderBys\": [\n" +
                    "    {\n" +
                    "      \"key\": \"id\",\n" +
                    "      \"asc\": true\n" +
                    "    }\n" +
                    "  ],\n" +
                    "  \"field\": \"xxx\"\n" +
                    "}"+
                    "</pre>"
    )
    @PostMapping("/rf-query")
    public BasePageResData queryRestful(@RequestBody AbcStaffReq abcStaffReq) {
        Pageable pageable = constructPagable(abcStaffReq);
        AbcStaffDto abcStaffDto = new AbcStaffDto();

        MapperTool.copyProperties(abcStaffReq, abcStaffDto);
        Page<AbcStaffDto> abcStaffDtoPage = abcStaffServiceImpl.findByAttributesInPage(abcStaffDto, false, pageable);
        BasePageResData<AbcStaffDto> myPageResData = new BasePageResData<>(abcStaffDtoPage,
                abcStaffReq.getCurrent(), abcStaffReq.getSize());

        return myPageResData;
    }

    @GetMapping("/rf-get-by-id/{id}")
    public BaseResData getByIdRestful(@PathVariable("id") Long id) {
        AbcStaffDto abcStaffDto = abcStaffServiceImpl.findById(id);
        return BaseResData.builder().data(abcStaffDto).build();
    }

    @DeleteMapping("/rf-delete/{id}")
    public BaseResponse deleteByIdRestful(@PathVariable("id") Long id) {
        abcStaffServiceImpl.deleteById(id);
        return BaseResponse.success();
    }

}


