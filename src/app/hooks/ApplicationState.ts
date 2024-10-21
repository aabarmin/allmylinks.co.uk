import { Block } from "../model/Block";
import { Page } from "../model/Page";

export class ApplicationState {
  private currentPageId: number = 1;
  private currentBlockId?: number = undefined;

  // should be private and accessible via getter only
  pages: Page[] = [
    new Page(this.currentPageId, "Home")
  ];

  public getCurrentPage(): Page {
    return this.pages.filter(p => p.id == this.currentPageId)[0];
  }

  public getCurrentBlock(): Block<any> | undefined {
    if (this.currentBlockId == undefined) {
      return undefined;
    }
    return this.getBlock(this.currentBlockId);
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

  public withCurrentBlock(block: Block<any>): ApplicationState {
    const newState = new ApplicationState();
    newState.currentPageId = this.currentPageId;
    newState.currentBlockId = block.id;
    newState.pages = this.pages;
    return newState;
  }

  public withUpdatedBlock<T>(block: Block<T>, callback: (current: T) => T): ApplicationState {
    const newState = new ApplicationState();
    newState.currentPageId = this.currentPageId;
    newState.currentBlockId = this.currentBlockId;
    newState.pages = this.pages.map(p => {
      return p.withUpdatedBlock(block, callback);
    });
    return newState;
  }

  public withBlock(page: Page, block: Block<any>): ApplicationState {
    const newState = new ApplicationState();
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
}