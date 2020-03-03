package com.ngbs.service;

import com.ngbs.common.ServerResponse;
import com.ngbs.pojo.Card;

public interface ICardService {

    ServerResponse add(Integer userId, Card card);

}
