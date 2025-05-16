export class PageModel {
    pageId: number;
    pageTitle: string;

    constructor(pageId: number, pageTitle: string) {
        this.pageId = pageId;
        this.pageTitle = pageTitle;
    }
}