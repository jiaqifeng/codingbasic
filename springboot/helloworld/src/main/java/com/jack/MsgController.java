package com.jack;

import java.util.Map;
import java.util.HashMap;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/msg")
public class MsgController {

    Map<Integer, String> data=new HashMap();
    int idNext=0;
    
    @RequestMapping(method = RequestMethod.POST)
    public String receive(@RequestBody String json) {
	System.out.println("get json="+json);
	data.put(++idNext, json);
	return String.valueOf(idNext);
    }

    @RequestMapping(method = RequestMethod.GET)
    public String list(@RequestParam(value = "id", required = true) int id) {
	String json=data.get(id);
	//for (Integer i : data.ent
	if (json!=null)
	    return "success:"+json;
	return "fail, could not find id="+id+",idNext="+idNext;
    }
}

