/** @type {import('next').NextConfig} */
const nextConfig = {
  images: {
    remotePatterns: [
      {
        protocol: 'https',
        hostname: 'no0tkfarxdryefpq.public.blob.vercel-storage.com',
        port: ''
      }
    ]
  }
};

export default nextConfig;
