import MobileFrame from "@/app/components/MobileFrame";
import { blocksToReactNodes, recordsToBlocks } from "@/lib/blockUtils";
import { getDbClient } from "@/lib/server/dbClient";
import Box from "@mui/material/Box";

export default async function Page({ params }: { params: Promise<{ slug: string }> }) {
  const { slug } = await params;
  const profile = await getDbClient().profile.findFirst({
    where: { link: slug },
    include: { pages: true }
  })
  if (!profile) {
    return (
      <Box>
        <h1>Page not found</h1>
      </Box>
    )
  }
  const pages = profile.pages;
  const homePage = pages.filter(p => p.isHome)[0];
  if (!homePage) {
    return (
      <Box>
        <h1>Home page not found</h1>
      </Box>
    )
  }
  const pageBlocks = await getDbClient().block.findMany({
    where: { pageId: homePage.id, isDeleted: false },
    orderBy: { order: 'asc' }
  })
  const nodes: React.ReactNode[] = blocksToReactNodes(recordsToBlocks(pageBlocks))

  return (
    <Box sx={{
      display: 'flex',
      justifyContent: 'center'
    }}>
      <MobileFrame blocks={nodes} />
    </Box>
  );
}
