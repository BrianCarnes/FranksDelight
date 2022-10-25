import { Typography } from '@mui/material';

function PageHeader() {
  const currentUser = JSON.parse(localStorage.getItem('user') || '{}');


  return (
    <>
      <Typography variant="h3" component="h3" gutterBottom>
        User Settings
      </Typography>
      <Typography variant="subtitle2">
        {currentUser.firstName}, welcome to your settings panel.
      </Typography>
    </>
  );
}

export default PageHeader;
