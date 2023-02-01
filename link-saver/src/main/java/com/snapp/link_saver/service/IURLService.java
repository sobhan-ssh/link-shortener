package com.snapp.link_saver.service;

import com.snapp.link_saver.dto.URLDto;
import com.snapp.link_saver.persistence.entity.URL;

public interface IURLService {

    URL persistUrl(URLDto urlDto);

}
