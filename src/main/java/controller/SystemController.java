package controller;

import entity.Permission;
import entity.Role;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pagemodel.DataGrid;
import pagemodel.Userinfo;
import service.SystemService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class SystemController {
    @Autowired
    SystemService systemservice;

    @RequestMapping("/useradmin")
    String useradmin() {
        return "system/useradmin";
    }

    @RequestMapping("/roleadmin")
    String roleadmin() {
        return "system/roleadmin";
    }

    @RequestMapping("/permissionadmin")
    String permissionadmin() {
        return "system/permissionadmin";
    }

    @RequestMapping(value = "/userlist", method = RequestMethod.GET)
    @ResponseBody
    DataGrid<Userinfo> userlist(@RequestParam("current") int current, @RequestParam("rowCount") int rowCount) {
        int total = systemservice.getUsers().size();
        List<User> userlist = systemservice.getpageusers(current, rowCount);
        List<Userinfo> users = new ArrayList<Userinfo>();
        for (User user : userlist) {
            Userinfo u = new Userinfo();
            u.setId(user.getId());
            u.setAge(user.getAge());
            u.setPassword(user.getPassword());
            u.setTel(user.getTel());
            u.setUsername(user.getUsername());
            String rolename = "";
            List<Role> roles = user.getRoles();
            if (roles != null) {
                for (Role role : roles) {
                    rolename = "," + role.getRoleName();
                }
                if (rolename.length() > 0)
                    rolename = rolename.substring(1, rolename.length());

                u.setRolelist(rolename);
            }
            users.add(u);
        }
        DataGrid<Userinfo> grid = new DataGrid<Userinfo>();
        grid.setCurrent(current);
        grid.setRows(users);
        grid.setRowCount(rowCount);
        grid.setTotal(total);
        return grid;
    }

    @RequestMapping(value = "/user/{uid}", method = RequestMethod.GET)
    @ResponseBody
    User getuserinfo(@PathVariable("uid") int userid) {
        return systemservice.getUserByid(userid);
    }

    @RequestMapping(value = "/rolelist", method = RequestMethod.GET)
    @ResponseBody
    List<Role> getroles() {
        return systemservice.getRoles();
    }

    @RequestMapping(value = "/roles", method = RequestMethod.GET)
    @ResponseBody
    DataGrid<Role> getallroles(@RequestParam("current") int current, @RequestParam("rowCount") int rowCount) {
        List<Role> roles = systemservice.getRoleinfo();
        List<Role> rows = systemservice.getpageRoleinfo(current, rowCount);
        DataGrid<Role> grid = new DataGrid<Role>();
        grid.setCurrent(current);
        grid.setRowCount(rowCount);
        grid.setTotal(roles.size());
        grid.setRows(rows);
        return grid;
    }

    @RequestMapping(value = "/deleteuser/{uid}", method = RequestMethod.GET)
    String deleteuser(@PathVariable("uid") int uid) {
        systemservice.deleteuser(uid);
        return "system/useradmin";
    }

    @RequestMapping(value = "/adduser", method = RequestMethod.POST)
    String adduser(@ModelAttribute("user") User user, @RequestParam(value = "rolename[]", required = false) String[] rolename) {
        if (rolename == null)
            systemservice.adduser(user);
        else
            systemservice.adduser(user, rolename);
        return "forward:/useradmin";
    }

    @RequestMapping(value = "/updateuser/{uid}", method = RequestMethod.POST)
    String updateuser(@PathVariable("uid") int uid, @ModelAttribute("user") User user, @RequestParam(value = "roleName[]", required = false) String[] roleName) {
        systemservice.updateuser(uid, user, roleName);
        return "system/useradmin";
    }


    @RequestMapping(value = "permissionlist", method = RequestMethod.GET)
    @ResponseBody
    List<Permission> getPermisions() {
        return systemservice.getPermisions();
    }

    @RequestMapping(value = "addrole", method = RequestMethod.POST)
    String addrole(@RequestParam("roleName") String roleName, @RequestParam(value = "permissionName[]") String[] permissionName) {
        Role r = new Role();
        r.setRoleName(roleName);
        systemservice.addRole(r, permissionName);
        return "forward:/roleadmin";
    }

    @RequestMapping(value = "/deleterole/{rid}", method = RequestMethod.GET)
    String deleterole(@PathVariable("rid") int rid) {
        systemservice.deleterole(rid);
        return "system/roleadmin";
    }

    @RequestMapping(value = "roleinfo/{rid}", method = RequestMethod.GET)
    @ResponseBody
    Role getRolebyrid(@PathVariable("rid") int rid) {
        return systemservice.getRolebyid(rid);
    }

    @RequestMapping(value = "updaterole/{rid}", method = RequestMethod.POST)
    String updaterole(@PathVariable("rid") int rid, @RequestParam(value = "permissionname[]") String[] permissionnames) {
        systemservice.deleterolepermission(rid);
        systemservice.updaterole(rid, permissionnames);
        return "system/roleadmin";
    }


    @RequestMapping(value = "permissions", method = RequestMethod.GET)
    @ResponseBody
    DataGrid<Permission> getpermissions(@RequestParam("current") int current, @RequestParam("rowCount") int rowCount) {
        List<Permission> p = systemservice.getPermisions();
        List<Permission> list = systemservice.getPagePermisions(current, rowCount);
        DataGrid<Permission> grid = new DataGrid<Permission>();
        grid.setCurrent(current);
        grid.setRowCount(rowCount);
        grid.setTotal(p.size());
        grid.setRows(list);
        return grid;
    }

    @RequestMapping(value = "addpermission", method = RequestMethod.POST)
    String addpermission(@RequestParam("permissionName") String permissionName) {
        systemservice.addPermission(permissionName);
        return "system/permissionadmin";
    }


    @RequestMapping(value = "deletepermission/{pid}", method = RequestMethod.GET)
    String deletepermission(@PathVariable("pid") int pid) {
        systemservice.deletepermission(pid);
        return "system/permissionadmin";
    }
}
