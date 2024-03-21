import * as React from 'react';
import AppBar from '@mui/material/AppBar';
import Box from '@mui/material/Box';
import Toolbar from '@mui/material/Toolbar';
import Typography from '@mui/material/Typography';
import Button from '@mui/material/Button';
import IconButton from '@mui/material/IconButton';
import Link from '@mui/material/Link';

export default function ButtonAppBar() {
  return (
    <AppBar sx={{ position: 'fixed', left: '18%', top: '0.5vh', width: '65%', backgroundColor: '#f0f0f0' }}>
      <Toolbar>
        <IconButton
          size="large"
          edge="start"
          color="inherit"
          aria-label="menu"
          sx={{ mr: 2 }}
        />
        <Button>
          <Link href="/" underline="none" color={"black"}>
            {"Balance of Account"}
          </Link>
        </Button>
      </Toolbar>
    </AppBar>
  );
}