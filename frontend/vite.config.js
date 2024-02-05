import { fileURLToPath, URL } from 'node:url'

import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
import path from 'node:path'

export default defineConfig({
  plugins: [
    vue(),
  ],
  resolve: {
    alias: {
      '@': fileURLToPath(new URL('./src', import.meta.url))
    }
  },
  // server:{
  //   //hmr:false,
  //   cors:"*",
  //   proxy:{
  //     '/groupcall':{
  //       target:'http://localhost:8443',
  //       ws:true,
  //       secure:true,
  //       changeOrigin:true,
  //       rewrite:(path)=>path.replace(/^\/gruopcall/,''),
  //     }
  //   }
  // }
})
