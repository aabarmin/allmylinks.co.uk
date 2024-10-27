import { PrismaClient } from "@prisma/client";

const prisma = new PrismaClient();

async function main() {
  const testProfile = await prisma.profile.upsert({
    where: { link: 'test' },
    update: { updatedAt: new Date() },
    create: {
      link: 'test',
      active: true,
      userId: 1,
      updatedAt: new Date()
    }
  })
  const testHomePage = await prisma.page.findFirst({
    where: { profileId: testProfile.id, isHome: true }
  });
  if (!testHomePage) {
    await prisma.page.create({
      data: {
        profileId: testProfile.id,
        title: "Home",
        isHome: true
      }
    })
  }
  const testAnotherPage = await prisma.page.findFirst({
    where: { profileId: testProfile.id, title: "Another Page" }
  });
  if (!testAnotherPage) {
    await prisma.page.create({
      data: {
        profileId: testProfile.id,
        title: "Another Page"
      }
    })
  }
}

main()
  .then(async () => {
    await prisma.$disconnect()
  })
  .catch(async (e) => {
    console.error(e);
    prisma.$disconnect()
    process.exit(1)
  })