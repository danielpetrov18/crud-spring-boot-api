package com.example.housing.mapper;

public interface IMapper<A,B> {

    B mapTo(A a);

    A mapFrom(B b);

}