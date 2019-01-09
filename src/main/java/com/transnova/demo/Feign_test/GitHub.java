package com.transnova.demo.Feign_test;

import feign.Param;
import feign.RequestLine;

import java.util.List;

public interface GitHub {
    @RequestLine("GET /repos/{owner}/{repo}/contributors")
    List<String> contributors(@Param("owner")String owner, @Param("repo")String repo);
}
