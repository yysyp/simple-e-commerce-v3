package ps.demo.common;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class BaseController {

    protected Pageable constructPagable(BasePageReq BasePageReq) {
        List<BasePageReq.OrderBy> orderByList = BasePageReq.getOrderBys();
        Sort sort = Sort.unsorted();
        if (CollectionUtils.isNotEmpty(orderByList)) {
            List<Sort.Order> orders = new ArrayList<>();
            for (BasePageReq.OrderBy orderBy : orderByList) {
                if (orderBy.getAsc()) {
                    orders.add(Sort.Order.asc(orderBy.getKey()));
                } else {
                    orders.add(Sort.Order.desc(orderBy.getKey()));
                }
            }
            sort = Sort.by(orders);
        }
        Pageable pageable = PageRequest.of((int) (BasePageReq.getCurrent() - 1), (int) BasePageReq.getSize(), sort);
        return pageable;
    }

    protected void initBaseCreateModifyTs(BaseDto myBaseDto) {
        //LoginUserDetail loginUserDetail = MyPrincipalUtils.getCurrentUser();
        if (myBaseDto.getCreatedOn() == null) {
            myBaseDto.setCreatedOn(Instant.now());
            myBaseDto.setIsActive(true);
            myBaseDto.setIsLogicalDeleted(false);
//            if (loginUserDetail != null) {
//                myBaseDto.setCreatedBy(loginUserDetail.getUserName());
//            } else {
//                myBaseDto.setCreatedBy("");
//            }
            myBaseDto.setCreatedBy("");
        }
        myBaseDto.setModifiedOn(Instant.now());
//        if (loginUserDetail != null) {
//            myBaseDto.setModifiedBy(loginUserDetail.getUserName());
//        } else {
//            myBaseDto.setModifiedBy("");
//        }
        myBaseDto.setModifiedBy("");
    }
}
