package com.mamaqunaer.wx.core;

import java.util.Arrays;
import java.util.Objects;

/**
 * Description: RetrofitNamedContextFactory.Specification实现类
 *
 * @Date 2020/4/27 15:46
 * @Author Zeti
 */
public abstract class RetrofitSpecification implements RetrofitNamedContextFactory.Specification {

    private String name;

    private Class<?>[] configuration;

    public RetrofitSpecification() {
    }

    public RetrofitSpecification(String name, Class<?>[] configuration) {
        this.name = name;
        this.configuration = configuration;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RetrofitSpecification that = (RetrofitSpecification) o;
        return Objects.equals(name, that.name) &&
                Arrays.equals(configuration, that.configuration);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, configuration);
    }

    @Override
    public String toString() {
        return "RetrofitClientSpecification{" +
                "name='" + name + "', " +
                "configuration=" + Arrays.toString(configuration) +
                "}";
    }

}
