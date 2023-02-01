package com.snapp.link_saver.service.impl;

import com.snapp.link_saver.dto.URLDto;
import com.snapp.link_saver.persistence.entity.URL;
import com.snapp.link_saver.persistence.repository.URLRepository;
import com.snapp.link_saver.service.IURLService;
import org.springframework.stereotype.Service;

@Service
public class URLServiceImpl implements IURLService {

    private final URLRepository urlRepository;

    public URLServiceImpl(URLRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    @Override
    public URL persistUrl(URLDto urlDto) {
        URL url = new URL();
        url.setShortLink(url.getShortLink());
        url.setMainLink(url.getMainLink());
        url.setClickCount(0);
        return urlRepository.save(url);
    }
}
