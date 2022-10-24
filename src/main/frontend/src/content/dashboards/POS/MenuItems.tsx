import React from 'react'
import { alpha, Avatar, Box, Button, Card, CardContent, Grid, styled, Typography } from '@mui/material'
import CRUD from '../../../hooks/CRUDService'
import menu from '../../../layouts/SidebarLayout/Header/Menu'
import { CartProvider, useCart } from "react-use-cart";
import Cart from './Cart';

const ItemWrapper = styled(Avatar)(
  ({ theme }) => `
    margin: ${theme.spacing(2, 0, 1, -0.5)};
    display: flex;
    align-items: center;
    justify-content: center;
    margin-right: ${theme.spacing(1)};
    padding: ${theme.spacing(0.5)};
    border-radius: 5px;
    height: ${theme.spacing(10)};
    width: ${theme.spacing(10)};
    background: ${
    theme.palette.mode === 'dark'
      ? theme.colors.alpha.trueWhite[30]
      : alpha(theme.colors.alpha.black[100], 0.07)
  };
  
    img {
      background: ${theme.colors.alpha.trueWhite[100]};
      padding: ${theme.spacing(0.5)};
      display: block;
      border-radius: inherit;
      height: ${theme.spacing(9.5)};
      width: ${theme.spacing(9.5)};
    }
`
);

type Props = {
  value: number;
}

const MenuItems = (props: Props) => {
  const getMenuItems = CRUD.getAll('/items');
  const [menu, setMenu] = React.useState(JSON.parse(localStorage.getItem('menu') || '[]'));
  const [ items, setItems ] = React.useState<any[]>([]);
  const menuItems = async () => {
    const menuItems = await getMenuItems.then((res) => res.Data);
    localStorage.setItem('menu', JSON.stringify(menuItems));
    return menuItems;
  }
  menuItems();

  const pageHandler = (): string => {
    switch (props.value) {
      case 0:
        return 'Appetizers';
      case 1:
        return 'Entrees';
      case 2:
        return 'Drinks';
      case 3:
        return 'Desserts';
    }
    return '';
  }
  let pageTitle: string = pageHandler()
  // sets items to the menu items that match the page title to lowercase and without the final 's'
  React.useEffect(() => {
    setItems(menu.filter((item: any) => item.category.toLowerCase() === pageTitle.toLowerCase().slice(0, -1)));
    console.log(items);
  }, [menu, pageTitle]);

  const { addItem } = useCart();


  return (
    <>
      <Box
        display="flex"
        alignItems="center"
        justifyContent="center"
        sx={{
          pb: 3
        }}
      >
        <Typography variant="h3">{pageTitle}</Typography>
      </Box>
      <Box
        sx={{
          display: 'flex',
          flexWrap: 'nowrap',
          justifyContent: 'center',
          mb: 3
        }}
      >
        <Grid container spacing={3} display="flex" alignItems="center" justifyContent="center">
          {items.map((item: any) => (
            <Grid xs={4} item key={item.id}>
              <Card>
                <CardContent
                  sx={{
                    display: 'flex',
                    flexDirection: 'column',
                    height: '100%',
                    justifyContent: 'center',
                    alignItems: 'center',
                    px: 1,
                  }}
                >
                  <ItemWrapper>
                    <img
                      alt={item.title}
                      src={item.image} />
                  </ItemWrapper>
                  <Typography variant='h5' noWrap>
                    {item.title}
                  </Typography>
                  <Box
                    sx={{
                      pt: 3,
                    }}
                  >
                    <Typography variant='h3' gutterBottom noWrap>
                      {item.quantity > 0 ? '$' + item.price : 'Out of Stock'}
                    </Typography>
                  </Box>
                  <Button
                    onClick={() => addItem(item)}
                    size='small'
                    variant='outlined'
                  >
                    +
                  </Button>
                </CardContent>
              </Card>
            </Grid>
          ))}
        </Grid>
          <Cart
            item={items}
          />
      </Box>
    </>
  );
};

export default MenuItems;
