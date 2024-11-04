/** @type {import('next').NextConfig} */
const nextConfig = {
  images: {
    remotePatterns: [
      {
        protocol: 'https',
        hostname: 'yqjsvtvuijrxouydakwm.supabase.co',
        port: '',
        search: '',
      }
    ]
  }
};

export default nextConfig;
