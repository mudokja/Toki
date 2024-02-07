import { localaxios } from "./BaseAxios"

const local = localaxios();

async function friendListSearch(isFriend, success, fail) {
    await local.get(`/api/v1/friends?is_friend=${isFriend}`)
        .then(success)
        .catch(fail)
}

async function friendAcceptanceRejection(userId, respondFriend, success, fail) {
    const requestData = {
        userId: userId,
        respondFriend: respondFriend,
    }
    await local.put('/api/v1/friends', JSON.stringify(requestData))
        .then(success)
        .catch(fail)
}

async function friendDelete(friendId, success, fail) {
    await local.delete(`/api/v1/friends/${friendId}`)
        .then(success)
        .catch(fail)
}

export {
    friendListSearch,
    friendAcceptanceRejection,
    friendDelete,
}