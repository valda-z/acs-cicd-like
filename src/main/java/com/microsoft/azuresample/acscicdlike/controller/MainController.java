package com.microsoft.azuresample.acscicdlike.controller;

import com.microsoft.azuresample.acscicdlike.model.Like;
import com.microsoft.azuresample.acscicdlike.model.LikeDAO;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class MainController {
    static final Logger LOG = LoggerFactory.getLogger(MainController.class);

    LikeDAO dao=new LikeDAO();

    @RequestMapping(value = "/api/like/LikeAdd", method = { RequestMethod.POST })
    public
    @ResponseBody
    String addLike(@RequestBody Like item) {
        LOG.info("Add like.");
        String ret = dao.addLike(item.getId());
        return ret;
    }

    @RequestMapping(value = "/api/like/Like", method = { RequestMethod.POST })
    public
    @ResponseBody
    Like getLike(@RequestBody Like item) {
        LOG.info("Get like item.");
        Like ret = dao.query(item.getId());
        return ret;
    }

    @RequestMapping(value = "/", method = { RequestMethod.GET })
    public
    @ResponseBody
    String probe() {
        LOG.info("Probe.");
        return "OK";
    }
}

