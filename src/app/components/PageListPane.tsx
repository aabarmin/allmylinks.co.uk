export function PageListPane() {
  // const { state, dispatch } = useAppState();
  // const navigateBack = () => dispatch(new LeftPanelChangeRequest('page-builder'))
  // const addNewPage = () => dispatch(new PageAddRequest(new Page(
  //   state.getPages().length + 1,
  //   'Newly added page'
  // )))
  // const pages = state.getPages();
  // const [showDialog, setShowDialog] = useState<string>('');
  // const [toDelete, setToDelete] = useState<number>(0);
  // const tryDelete = (pageId: number) => {
  //   if (pages.length == 1) {
  //     setShowDialog('cant-delete')
  //   } else {
  //     setToDelete(pageId);
  //     setShowDialog('confirm-delete')
  //   }
  // }
  // const performDelete = () => {
  //   const page = state.getPage(toDelete)
  //   if (page != undefined) {
  //     dispatch(new PageDeleteRequest(page))
  //   }
  //   setShowDialog('')
  //   setToDelete(0);
  // }
  // const updateTitle = (pageId: number, value: string) => {
  //   const page = state.getPage(pageId)
  //   if (page != undefined) {
  //     dispatch(new PageUpdateRequest(
  //       page,
  //       (p) => {
  //         const newPage = new Page(p.id, value)
  //         newPage.blocks = page.blocks
  //         return newPage
  //       }
  //     ))
  //   }
  // }

  return (
    <div>
      This page requires refactoring and not in use at the moment.
    </div>
  )

  // return (
  //   <Box>
  //     <Sheet>
  //       <Button startDecorator={<ArrowBack />} onClick={navigateBack}>
  //         Back
  //       </Button>
  //     </Sheet>
  //     <Sheet>
  //       <Typography level="h1">Manage pages</Typography>
  //       <Typography>
  //         Add pages to your profile by clicking the Add page button. Add link to pages by using Link page block.
  //       </Typography>
  //     </Sheet>
  //     <Sheet sx={{ height: '16px' }} />
  //     <List>
  //       {pages.map(p => {
  //         return (
  //           <ListItem key={p.id}>
  //             <ListItemContent>
  //               <Input value={p.title} onChange={(e) => updateTitle(p.id, e.target.value)} />
  //             </ListItemContent>
  //             <ListItemButton onClick={() => tryDelete(p.id)}>
  //               <Delete />
  //             </ListItemButton>
  //           </ListItem>
  //         )
  //       })}
  //       <ListItem key='add-new'>
  //         <ListItemContent>
  //           <Button onClick={addNewPage}>
  //             Add new page
  //           </Button>
  //         </ListItemContent>
  //       </ListItem>
  //     </List>

  //     <Modal open={showDialog == 'confirm-delete'} onClose={() => setShowDialog('')}>
  //       <ModalDialog>
  //         <DialogTitle>
  //           <WarningRounded />
  //           Confirmation
  //         </DialogTitle>
  //         <Divider />
  //         <DialogContent>
  //           Do you really want to delete a page?
  //         </DialogContent>
  //         <DialogActions>
  //           <Button
  //             startDecorator={<Delete />}
  //             variant="solid"
  //             onClick={performDelete}>
  //             Delete
  //           </Button>
  //           <Button variant="plain" onClick={() => setShowDialog('')}>
  //             Cancel
  //           </Button>
  //         </DialogActions>
  //       </ModalDialog>
  //     </Modal>

  //     <Modal open={showDialog == 'cant-delete'} onClose={() => setShowDialog('')}>
  //       <ModalDialog>
  //         <DialogTitle>
  //           <WarningRounded />
  //           Warning
  //         </DialogTitle>
  //         <Divider />
  //         <DialogContent>
  //           Can not delete the one page
  //         </DialogContent>
  //         <DialogActions>
  //           <Button variant="plain" onClick={() => setShowDialog('')}>
  //             Ok
  //           </Button>
  //         </DialogActions>
  //       </ModalDialog>
  //     </Modal>
  //   </Box>
  // )
}