package org.zdjecia.model.converter;

public interface Converter<F,T> {
    T convert(F from);
}
