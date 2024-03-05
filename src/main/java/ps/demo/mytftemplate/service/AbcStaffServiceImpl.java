

package ps.demo.mytftemplate.service;

import java.util.ArrayList;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Collection;

import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.math.*;

import java.util.stream.Collectors;

import com.google.common.collect.ImmutableMap;
import org.springframework.transaction.annotation.Transactional;

import ps.demo.mytftemplate.dto.AbcStaffDto;
import ps.demo.mytftemplate.dto.AbcStaffReq;
import ps.demo.mytftemplate.entity.AbcStaff;
import ps.demo.mytftemplate.repository.AbcStaffDao;
import ps.demo.common.MapperTool;

import jakarta.persistence.criteria.*;

import lombok.*;

import java.util.*;
import java.math.*;

@Slf4j
@Service
public class AbcStaffServiceImpl {

    @Autowired
    AbcStaffDao abcStaffDao;

    @Transactional
    public AbcStaffDto save(AbcStaffDto abcStaffDto) {
        AbcStaff abcStaff = new AbcStaff();
        MapperTool.copyProperties(abcStaffDto, abcStaff);
        AbcStaff entity = abcStaffDao.save(abcStaff);
        MapperTool.copyProperties(entity, abcStaffDto);
        return abcStaffDto;
    }

    @Transactional
    public List<AbcStaffDto> saveAll(Collection<AbcStaffDto> abcStaffDtoList) {
        if (CollectionUtils.isEmpty(abcStaffDtoList)) {
            return new ArrayList<>();
        }
        List<AbcStaffDto> result = new ArrayList<>();
        for (AbcStaffDto abcStaffDto : abcStaffDtoList) {
            result.add(save(abcStaffDto));
        }
        return result;
    }

    public AbcStaffDto findById(Long id) {
        Optional<AbcStaff> abcStaffOptional = abcStaffDao.findById(id);
        if (abcStaffOptional.isEmpty()) {
            return null;
        }
        AbcStaffDto abcStaffDto = new AbcStaffDto();
        abcStaffOptional.ifPresent(e -> {
            MapperTool.copyProperties(e, abcStaffDto);
        });
        return abcStaffDto;
    }

    public List<AbcStaffDto> findByAttribute(String attributeName, Object attribute) {
        Specification<AbcStaff> spec = new Specification<AbcStaff>() {
            @Override
            public Predicate toPredicate(Root<AbcStaff> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                return cb.and(cb.equal(root.get(attributeName), attribute));
            }
        };
        List<AbcStaff> abcStaffList = abcStaffDao.findAll(spec);
        return MapperTool.copyAndConvertItems(abcStaffList, AbcStaffDto.class);
    }

    //@Transactional(readOnly = true)
    public List<AbcStaffDto> findAll() {
        List<AbcStaff> abcStaffList = abcStaffDao.findAll();
        List<AbcStaffDto> abcStaffDtoList = new ArrayList<>();
        for (AbcStaff abcStaff : abcStaffList) {
            AbcStaffDto abcStaffDto = new AbcStaffDto();
            MapperTool.copyProperties(abcStaff, abcStaffDto);
            abcStaffDtoList.add(abcStaffDto);
        }
        return abcStaffDtoList;
    }

    public Page<AbcStaffDto> findInPage(Pageable pageable) {
        Page<AbcStaff> page = abcStaffDao.findAll(pageable);
        Page<AbcStaffDto> pageDto = page.map((e) -> {
            AbcStaffDto abcStaffDto = new AbcStaffDto();
            MapperTool.copyProperties(e, abcStaffDto);
            return abcStaffDto;
        });
        return pageDto;
    }

    public List<AbcStaffDto> findByAttributes(AbcStaffDto abcStaffDto, boolean orLike) {
        AbcStaff abcStaff = new AbcStaff();
        MapperTool.copyProperties(abcStaffDto, abcStaff);
        Specification<AbcStaff> spec = constructSpecification(abcStaff, orLike);
        List<AbcStaff> abcStaffList = abcStaffDao.findAll(spec);
        return MapperTool.copyAndConvertItems(abcStaffList, AbcStaffDto.class);
    }

    public Page<AbcStaffDto> findByAttributesInPage(AbcStaffDto abcStaffDto, boolean orLike, Pageable pageable) {
        AbcStaff abcStaff = new AbcStaff();
        MapperTool.copyProperties(abcStaffDto, abcStaff);
        Specification<AbcStaff> spec = constructSpecification(abcStaff, orLike);

        Page<AbcStaff> page = abcStaffDao.findAll(spec, pageable);
        Page<AbcStaffDto> pageDto = page.map((e) -> {
            AbcStaffDto abcStaffDtoResult = new AbcStaffDto();
            MapperTool.copyProperties(e, abcStaffDtoResult);
            return abcStaffDtoResult;
        });
        return pageDto;
    }

    private Specification<AbcStaff> constructSpecification(AbcStaff abcStaff, boolean orLike) {
        Specification<AbcStaff> spec = new Specification<AbcStaff>() {
            @Override
            public Predicate toPredicate(Root<AbcStaff> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate predicate = null;
                if (orLike) {
                
                    predicate = orLike(predicate, cb, root,"firstName", abcStaff.getFirstName());
                    predicate = orLike(predicate, cb, root,"lastName", abcStaff.getLastName());
                    predicate = orEqual(predicate, cb, root,"age", abcStaff.getAge());
                    predicate = orEqual(predicate, cb, root,"score", abcStaff.getScore());
                    predicate = orEqual(predicate, cb, root,"passed", abcStaff.getPassed());
                    predicate = orEqual(predicate, cb, root,"birthday", abcStaff.getBirthday());
                    predicate = orLike(predicate, cb, root,"comments", abcStaff.getComments());

                } else {
                
                    if (null != abcStaff.getFirstName()) {
                        predicate = andEqual(predicate, cb, root, "firstName", abcStaff.getFirstName());
                    }
                    if (null != abcStaff.getLastName()) {
                        predicate = andEqual(predicate, cb, root, "lastName", abcStaff.getLastName());
                    }
                    if (null != abcStaff.getAge()) {
                        predicate = andEqual(predicate, cb, root, "age", abcStaff.getAge());
                    }
                    if (null != abcStaff.getScore()) {
                        predicate = andEqual(predicate, cb, root, "score", abcStaff.getScore());
                    }
                    if (null != abcStaff.getPassed()) {
                        predicate = andEqual(predicate, cb, root, "passed", abcStaff.getPassed());
                    }
                    if (null != abcStaff.getBirthday()) {
                        predicate = andEqual(predicate, cb, root, "birthday", abcStaff.getBirthday());
                    }
                    if (null != abcStaff.getComments()) {
                        predicate = andEqual(predicate, cb, root, "comments", abcStaff.getComments());
                    }
                
                }

                return predicate;
            }
        };
        return spec;
    }

    private Predicate andEqual(Predicate predicate, CriteriaBuilder cb, Root<AbcStaff> root, String attributeName, Object attributeValue) {
        if (null == attributeValue) {
            return predicate;
        }
        if (null == predicate) {
            return cb.and(cb.equal(root.get(attributeName), attributeValue));
        } else {
            return cb.and(predicate, cb.equal(root.get(attributeName), attributeValue));
        }
    }
    private Predicate orEqual(Predicate predicate, CriteriaBuilder cb, Root<AbcStaff> root, String attributeName, Object attributeValue) {
        if (null == attributeValue) {
            return predicate;
        }
        if (null == predicate) {
            return cb.or(cb.equal(root.get(attributeName), attributeValue));
        } else {
            return cb.or(predicate, cb.equal(root.get(attributeName), attributeValue));
        }
    }
    private Predicate orLike(Predicate predicate, CriteriaBuilder cb, Root<AbcStaff> root, String attributeName, String attributeValue) {
        if (null == attributeValue) {
            return predicate;
        }
        if (null == predicate) {
            return cb.or(cb.like(root.get(attributeName), attributeValue));
        } else {
            return cb.or(predicate, cb.like(root.get(attributeName), attributeValue));
        }
    }

    @Transactional
    public void deleteById(Long id) {
        abcStaffDao.deleteById(id);
    }

    @Transactional
    public void deleteAll(Collection<Long> idList) {
        if (CollectionUtils.isEmpty(idList)) {
            return;
        }
        for (Long id : idList) {
            deleteById(id);
        }
    }

}


