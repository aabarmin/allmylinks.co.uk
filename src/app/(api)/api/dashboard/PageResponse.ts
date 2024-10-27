import { Page } from "@prisma/client"

export type SinglePageResponse = {
  id: number,
  title: string,
  blocks: object[]
}

export type PageResponse = {
  currentPageId: number,
  pages: SinglePageResponse[]
}

function toSinglePageResponse(page: Page): SinglePageResponse {
  return {
    id: page.id,
    title: page.title,
    blocks: []
  }
}

export function toPageResponse(pages: Page[]): PageResponse {
  const homePageId = pages
    .filter(p => p.isHome)
    .map(p => p.id)[0]

  return {
    currentPageId: homePageId,
    pages: pages.map(toSinglePageResponse)
  }
}