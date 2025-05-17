import type { BlockResponse } from "./BlockModel";

export class PageModel {
  pageId: number;
  pageTitle: string;
  pageProps: PageProps;
  pageBlocks: BlockResponse[];

  constructor(
    pageId: number,
    pageTitle: string,
    pageProps: PageProps,
    pageBlocks: BlockResponse[]
  ) {
    this.pageId = pageId
    this.pageTitle = pageTitle
    this.pageProps = pageProps
    this.pageBlocks = pageBlocks
  }
}

export class PageProps {
  type: string;
  backgroundColor: string;

  constructor(
    type: string,
    backgroundColor: string
  ) {
    this.type = type;
    this.backgroundColor = backgroundColor;
  }
}