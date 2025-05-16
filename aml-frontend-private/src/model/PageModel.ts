import type { BlockModel } from "./BlockModel";

export class PageModel {
  pageId: number;
  pageTitle: string;
  pageProps: PageProps;
  pageBlocks: BlockModel[];

  constructor(
    pageId: number,
    pageTitle: string,
    pageProps: PageProps,
    pageBlocks: BlockModel[]
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