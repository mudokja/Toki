/*
 * (C) Copyright 2014 Kurento (http://kurento.org/)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.toki.backend.webrtc.service;

import com.toki.backend.webrtc.dto.UserRoomSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketSession;

import java.util.concurrent.ConcurrentHashMap;
/**
 * Map of users registered in the system. This class has a concurrent hash map to store users, using
 * its name as key in the map.
 *
 *
 * @author Boni Garcia (bgarcia@gsyc.es)
 * @author Micael Gallego (micael.gallego@gmail.com)
 * @author Ivan Gracia (izanmail@gmail.com)
 */
@Service
@RequiredArgsConstructor
public class TokiUserRegistry {

  private final ConcurrentHashMap<String, UserRoomSession> usersByName = new ConcurrentHashMap<>();
  private final ConcurrentHashMap<String, UserRoomSession> usersBySessionId = new ConcurrentHashMap<>();

  public void register(UserRoomSession user) {
    usersByName.put(user.getName(), user);
    usersBySessionId.put(user.getSession().getId(), user);
  }

  public UserRoomSession getByName(String name) {
    return usersByName.get(name);
  }

  public UserRoomSession getBySession(WebSocketSession session) {
    return usersBySessionId.get(session.getId());
  }

  public boolean exists(String name) {
    return usersByName.keySet().contains(name);
  }

  public UserRoomSession removeBySession(WebSocketSession session) {
    final UserRoomSession user = getBySession(session);
    usersByName.remove(user.getName());
    usersBySessionId.remove(session.getId());
    return user;
  }

}
