package com.snapp.link_saver.service.impl;

import com.snapp.link_saver.dto.ClickRatioMessage;
import com.snapp.link_saver.dto.PersistURLMessage;
import com.snapp.link_saver.persistence.entity.URL;
import com.snapp.link_saver.persistence.repository.URLRepository;
import com.snapp.link_saver.service.IURLService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class URLServiceImpl implements IURLService {

    private final URLRepository urlRepository;

    public URLServiceImpl(URLRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    @Override
    public URL persistUrl(PersistURLMessage urlMessage) {
        URL url = new URL();
        url.setShortLink(urlMessage.getShortURL());
        url.setMainLink(urlMessage.getOriginalURL());
        url.setClickCount(0);
        url.setCreatedTime(new Date());
        return urlRepository.save(url);
    }

    @Override
    public void updateClickRatio(ClickRatioMessage clickRatioMessage) {
        Optional<URL> optional = urlRepository.findByShortLink(clickRatioMessage.getShortURL());
        if (optional.isPresent()) {
            URL url = optional.get();
            int current = url.getClickCount();
            url.setClickCount(++current);
            urlRepository.save(url);
        }
    }
}
