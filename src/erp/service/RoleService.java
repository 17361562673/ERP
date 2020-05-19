
package erp.service;

import erp.model.Role;
import erp.query.RoleQuery;

public interface RoleService extends BaseService<Role,RoleQuery>{
    public void updateGrantPerm(Integer roleId,String permIds);
}