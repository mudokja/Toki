<script setup>
import { register } from './WebRTC';
import { commonaxios } from '@/js/CommonAxios';
const register1=()=>{
register();

}
</script>

<template>
    <div >
        <video src="" width="200" height="200" id="display"></video>
    </div>
    안녕하세여
<body>
	<div id="container">
		<div id="wrapper">
			<div id="join" class="animate join">
				<h1>Join a Room</h1>
				<form onsubmit="register1(); return false;" accept-charset="UTF-8">
					<p>
						<input type="text" name="name" value="" id="name"
							placeholder="Username" required>
					</p>
					<p>
						<input type="text" name="room" value="" id="roomName"
							placeholder="Room" required>
					</p>
					<p class="submit">
						<input type="submit" name="commit" value="Join!">
					</p>
				</form>
			</div>
			<div id="room" style="display: none;">
				<h2 id="room-header"></h2>
				<div id="participants"></div>
				<input type="button" id="button-leave" onmouseup="leaveRoom();"
					value="Leave room">
			</div>
		</div>
	</div>
</body>
</template>

<style scoped>
#display{
    background-color: aqua;
}
</style>