import { localaxios } from "./BaseAxios";
const local=localaxios();
function roomCreate(roomData,success,fail){
    local.post('',JSON.stringify(roomData)).then(success).catch(fail);
}
function roomEnter(roomNum,success,fail){
    local.get(`${roomNum}`).then(success).catch(fail);
}
export{
roomCreate,
roomEnter,
}
