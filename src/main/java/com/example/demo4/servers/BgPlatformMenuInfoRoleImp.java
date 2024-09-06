package com.example.demo4.servers;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo4.mapper.BgPlatformMenuInfoRoleDao;
import com.example.demo4.pojo.po.BgPlatformMenuInfoRole;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class BgPlatformMenuInfoRoleImp extends ServiceImpl<BgPlatformMenuInfoRoleDao, BgPlatformMenuInfoRole> {

    public List<BgPlatformMenuInfoRole> BgPlatformMenuInfoRoleList(List<?> map,String roleId){
        List<BgPlatformMenuInfoRole> list = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        map.forEach(c -> {
            var bgPlatformMenuInfoRole = new BgPlatformMenuInfoRole();
            ObjectNode json = mapper.convertValue(c, ObjectNode.class);
            if(json.has("children")){
                var children = json.get("children");
                children.forEach(i -> {
                    var bgPlatformMenuInfoRole1 = new BgPlatformMenuInfoRole();
                    bgPlatformMenuInfoRole1.setRoleid(Integer.valueOf(roleId));
                    bgPlatformMenuInfoRole1.setBgPlatformMenuInfo(i.get("id").asLong());
                    list.add(bgPlatformMenuInfoRole1);
                });
            }else {
                System.out.println(json);
                if(json.has("id")){
                    bgPlatformMenuInfoRole.setRoleid(Integer.valueOf(roleId));
                    bgPlatformMenuInfoRole.setBgPlatformMenuInfo(json.get("id").asLong());
                    list.add(bgPlatformMenuInfoRole);
                }
            }
        });
        return list;
    }
}
