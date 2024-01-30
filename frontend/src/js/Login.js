import { localaxios } from "./BaseAxios";
const local = localaxios();
function login(login,success,fail){
local.get(`${login}`).then(success).catch(fail);
}
function logout(logout,success,fail){
    local.delete(`${logout}`).then(success).catch(fail)
}
export{
    login,
    logout,
}