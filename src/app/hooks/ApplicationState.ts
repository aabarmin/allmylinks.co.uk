import { Block } from "../model/Block";
import { Page } from "../model/Page";

export class ApplicationState {
  private currentPageId: number = 1;
  private currentBlockId?: number = undefined;
  private currentLeftPane: string = 'page-builder';
  private pages: Page[] = [
    new Page(this.currentPageId, "Home")
  ];

  public getPages(): Page[] {
    return this.pages;
  }

  public getCurrentLeftPane(): string {
    return this.currentLeftPane;
  }

  public getCurrentPage(): Page {
    return this.pages.filter(p => p.id == this.currentPageId)[0];
  }

  public getCurrentBlock(): Block<any> | undefined {
    if (this.currentBlockId == undefined) {
      return undefined;
    }
    return this.getBlock(this.currentBlockId);
  }

  public getPage(pageId: number): Page | undefined {
    const pages = this.pages.filter(p => p.id == pageId)
    if (pages.length == 0) {
      return undefined
    }
    return pages[0]
  }

  public getBlock(blockId: number): Block<any> | undefined {
    const blocks = this.pages
      .map(p => p.getBlock(blockId))
      .filter(b => b != undefined);

    if (blocks.length == 0) {
      return undefined;
    }
    return blocks[0];
  }

  public withLeftPane(paneAlias: string): ApplicationState {
    const newState = new ApplicationState();
    newState.currentLeftPane = paneAlias;
    newState.currentPageId = this.currentPageId;
    newState.currentBlockId = this.currentBlockId;
    newState.pages = this.pages;
    return newState;
  }

  public withCurrentBlock(block: Block<any>): ApplicationState {
    const newState = new ApplicationState();
    newState.currentLeftPane = this.currentLeftPane;
    newState.currentPageId = this.currentPageId;
    newState.currentBlockId = block.id;
    newState.pages = this.pages;
    return newState;
  }

  public withUpdatedBlock<T>(block: Block<T>, callback: (current: T) => T): ApplicationState {
    const newState = new ApplicationState();
    newState.currentLeftPane = this.currentLeftPane;
    newState.currentPageId = this.currentPageId;
    newState.currentBlockId = this.currentBlockId;
    newState.pages = this.pages.map(p => {
      return p.withUpdatedBlock(block, callback);
    });
    return newState;
  }

  public withBlock(page: Page, block: Block<any>): ApplicationState {
    const newState = new ApplicationState();
    newState.currentLeftPane = this.currentLeftPane;
    newState.currentPageId = this.currentPageId;
    newState.currentBlockId = this.currentBlockId;
    newState.pages = this.pages.map(p => {
      if (p.id != page.id) {
        return p;
      }
      return p.withBlock(block);
    });
    return newState;
  }

  public withPage(page: Page): ApplicationState {
    const newState = new ApplicationState();
    newState.currentLeftPane = this.currentLeftPane;
    newState.currentPageId = this.currentPageId;
    newState.currentBlockId = this.currentBlockId;
    newState.pages = [...this.pages, page]
    return newState;
  }

  public withoutPage(page: Page): ApplicationState {
    const newState = new ApplicationState();
    newState.currentLeftPane = this.currentLeftPane;
    newState.currentPageId = this.currentPageId;
    newState.currentBlockId = this.currentBlockId;
    newState.pages = this.pages.filter(p => p.id != page.id);
    return newState;
  }

  public withUpdatedPage(page: Page, callback: (page: Page) => Page): ApplicationState {
    const newState = new ApplicationState();
    newState.currentLeftPane = this.currentLeftPane;
    newState.currentPageId = this.currentPageId;
    newState.currentBlockId = this.currentBlockId;
    newState.pages = this.pages.map(p => {
      if (p.id != page.id) {
        return p;
      }
      return callback(p);
    })
    return newState;
  }
}