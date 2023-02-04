package com.snapp.link_saver.service;

import com.snapp.link_saver.dto.ClickRatioMessage;
import com.snapp.link_saver.dto.PersistURLMessage;
import com.snapp.link_saver.persistence.entity.URL;

public interface IURLService {

    URL persistUrl(PersistURLMessage urlMessage);
    void updateClickRatio(ClickRatioMessage clickRatioMessage);

}
