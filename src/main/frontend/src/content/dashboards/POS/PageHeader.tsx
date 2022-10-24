import React from 'react';
import { Typography, Avatar, Grid } from '@mui/material';
import { useTheme } from '@mui/material/styles';
import Tabs from '@mui/material/Tabs';
import Tab from '@mui/material/Tab';
import RestaurantIcon from '@mui/icons-material/Restaurant';
import CakeIcon from '@mui/icons-material/Cake';
import TapasIcon from '@mui/icons-material/Tapas';
import LocalDrinkIcon from '@mui/icons-material/LocalDrink';

function PageHeader( props: any ) {
  const theme = useTheme();


  return (
    <Tabs value={props.value} onChange={props.handleChange} aria-label="icon label tabs example" centered>
      <Tab icon={<TapasIcon />} />
      <Tab icon={<RestaurantIcon />} />
      <Tab icon={<LocalDrinkIcon />} />
      <Tab icon={<CakeIcon />} />
    </Tabs>
  );
}

export default PageHeader;