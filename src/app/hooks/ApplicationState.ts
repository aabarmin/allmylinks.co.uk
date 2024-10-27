import { DashboardResponse } from "../(api)/api/dashboard/DashboardResponse";
import { ProfileResponse } from "../(api)/api/dashboard/ProfileResponse";
import { Block } from "../model/Block";
import { Page } from "../model/Page";

export class ApplicationState {
  private currentPageId: number = 1;
  private currentBlockId?: number = undefined;
  private currentLeftPane: string = 'page-builder';
  private pages: Page[] = [
    new Page(this.currentPageId, "Home")
  ];
  private profile?: ProfileResponse;

  public getPages(): Page[] {
    return this.pages;
  }

  public getProfile(): ProfileResponse {
    if (this.profile == null) {
      throw new Error("Profile is not set");
    }
    return this.profile;
  }

  public getCurrentLeftPane(): string {
    return this.currentLeftPane;
  }

  public getCurrentPage(): Page {
    return this.pages.filter(p => p.id == this.currentPageId)[0];
  }

  public getCurrentBlock(): Block<object> | undefined {
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

  public getBlock(blockId: number): Block<object> | undefined {
    const blocks = this.pages
      .map(p => p.getBlock(blockId))
      .filter(b => b != undefined);

    if (blocks.length == 0) {
      return undefined;
    }
    return blocks[0];
  }

  public withInitialData(data: DashboardResponse): ApplicationState {
    const newState = new ApplicationState();
    newState.profile = data.profile;
    newState.currentPageId = data.pages.currentPageId;
    newState.pages = data.pages.pages.map(p => {
      return new Page(p.id, p.title); // convert json to Page, maybe there is better way to do this
    })
    return newState;
  }

  public withLeftPane(paneAlias: string): ApplicationState {
    const newState = new ApplicationState();
    newState.currentLeftPane = paneAlias;
    newState.currentPageId = this.currentPageId;
    newState.currentBlockId = this.currentBlockId;
    newState.pages = this.pages;
    newState.profile = this.profile;
    return newState;
  }

  public withCurrentPage(page: Page): ApplicationState {
    const newState = new ApplicationState();
    newState.currentLeftPane = this.currentLeftPane;
    newState.currentPageId = page.id;
    newState.currentBlockId = this.currentBlockId;
    newState.pages = this.pages;
    newState.profile = this.profile;
    return newState;
  }

  public withCurrentBlock(block: Block<object>): ApplicationState {
    const newState = new ApplicationState();
    newState.currentLeftPane = this.currentLeftPane;
    newState.currentPageId = this.currentPageId;
    newState.currentBlockId = block.id;
    newState.pages = this.pages;
    newState.profile = this.profile;
    return newState;
  }

  public withUpdatedBlock<T extends object>(block: Block<T>, callback: (current: T) => T): ApplicationState {
    const newState = new ApplicationState();
    newState.currentLeftPane = this.currentLeftPane;
    newState.currentPageId = this.currentPageId;
    newState.currentBlockId = this.currentBlockId;
    newState.pages = this.pages.map(p => {
      return p.withUpdatedBlock(block, callback);
    });
    newState.profile = this.profile;
    return newState;
  }

  public withBlock(page: Page, block: Block<object>): ApplicationState {
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
    newState.profile = this.profile;
    return newState;
  }

  public withPage(page: Page): ApplicationState {
    const newState = new ApplicationState();
    newState.currentLeftPane = this.currentLeftPane;
    newState.currentPageId = this.currentPageId;
    newState.currentBlockId = this.currentBlockId;
    newState.pages = [...this.pages, page]
    newState.profile = this.profile;
    return newState;
  }

  public withoutPage(page: Page): ApplicationState {
    const newState = new ApplicationState();
    newState.currentLeftPane = this.currentLeftPane;
    newState.currentPageId = this.currentPageId;
    newState.currentBlockId = this.currentBlockId;
    newState.pages = this.pages.filter(p => p.id != page.id);
    newState.profile = this.profile;
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
    newState.profile = this.profile;
    return newState;
  }
}