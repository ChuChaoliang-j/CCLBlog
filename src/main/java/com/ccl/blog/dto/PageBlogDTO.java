package com.ccl.blog.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author CCL
 * @date 2019/9/7 15:50
 */
@Data
public class PageBlogDTO {

    private Boolean showNextPage;
    private Boolean showLastPage;
    private Boolean showFirstPage;
    private Boolean showEndPage;
    private Integer page;
    private Integer endPage;
    private List<BlogDTO> blogs;
    private List<Integer> pages = new ArrayList<>();

    public void setPage(Integer page, Integer size, Integer count) {
        endPage = count % size == 0 ? count / size : count / size + 1;
        if (page > endPage) {
            page = endPage;
        }
        this.page = page;
        pages.add(page);
        for (int i = 1; i <= 3; i++) {
            //计算page左边
            if (page - i > 0) {
                pages.add(0, page - i);
            }
            //计算page右边
            if (page + i <= endPage) {
                pages.add(page + i);
            }
        }
        //计算上一页
        if (page == 1) {
            showLastPage = true;
        } else {
            showLastPage = false;
        }
        //计算下一页
        if (page.equals(endPage)) {
            showNextPage = true;
        } else {
            showNextPage = false;
        }
        //计算第一页
        if (pages.get(0) == 1) {
            showFirstPage = true;
        } else {
            showFirstPage = false;
        }
        //计算最后一页
        if (pages.get(pages.size() - 1).equals(endPage)) {
            showEndPage = true;
        } else {
            showEndPage = false;
        }
    }
}
