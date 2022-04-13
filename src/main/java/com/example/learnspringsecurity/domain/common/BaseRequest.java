package com.example.learnspringsecurity.domain.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;
import java.util.Objects;

/**
 * @author Muhammad Rezki Aprilan
 * @project learn-spring-security
 * @email muhammad.rezki@bankmandiri.co.id
 * @created 13/04/2022 - 11:09:45
 */
public class BaseRequest implements Serializable {

    @JsonIgnore
    @ApiModelProperty(
            hidden = true
    )
    private transient HttpServletRequest servletRequest;
    @JsonIgnore
    @ApiModelProperty(
            hidden = true
    )
    private transient HttpServletResponse servletResponse;

    public BaseRequest() {
    }

    public HttpServletRequest getServletRequest() {
        return this.servletRequest;
    }

    public HttpServletResponse getServletResponse() {
        return this.servletResponse;
    }


    @JsonIgnore
    public void setServletRequest(final HttpServletRequest servletRequest) {
        this.servletRequest = servletRequest;
    }

    @JsonIgnore
    public void setServletResponse(final HttpServletResponse servletResponse) {
        this.servletResponse = servletResponse;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseRequest that = (BaseRequest) o;
        return Objects.equals(servletRequest, that.servletRequest) && Objects.equals(servletResponse, that.servletResponse);
    }

    @Override
    public int hashCode() {
        return Objects.hash(servletRequest, servletResponse);
    }
}
