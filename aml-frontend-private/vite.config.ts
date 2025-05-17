import react from '@vitejs/plugin-react'
import { defineConfig } from 'vite'

// https://vite.dev/config/
export default defineConfig({
  plugins: [react()],
  server: {
    proxy: {
      '/private': 'http://localhost:8080',
      '/img': 'http://localhost:8080',
    }
  }
})
