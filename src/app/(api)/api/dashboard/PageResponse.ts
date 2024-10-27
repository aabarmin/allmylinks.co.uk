import { BlockResponse, toBlockResponse } from "./BlockResponse"
import { PageWithBlocks } from "./route"

export type SinglePageResponse = {
  id: number,
  title: string,
  blocks: BlockResponse[]
}

export type PageResponse = {
  currentPageId: number,
  pages: SinglePageResponse[]
}

function toSinglePageResponse(page: PageWithBlocks): SinglePageResponse {
  // todo, extract in correct order from the db
  page.blocks.sort((a, b) => a.order - b.order);

  return {
    id: page.id,
    title: page.title,
    blocks: page.blocks.map(toBlockResponse)
  }
}

export function toPageResponse(pages: PageWithBlocks[]): PageResponse {
  const homePageId = pages
    .filter(p => p.isHome)
    .map(p => p.id)[0]

  return {
    currentPageId: homePageId,
    pages: pages.map(toSinglePageResponse)
  }
}