/****** Object:  Table [dbo].[INVENTORY_TABLE]    Script Date: 11/16/2010 14:15:53 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[INVENTORY_TABLE]') AND type in (N'U'))
DROP TABLE [dbo].[INVENTORY_TABLE]
GO
/****** Object:  Table [dbo].[USERS_TABLE]    Script Date: 11/16/2010 14:15:53 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[USERS_TABLE]') AND type in (N'U'))
DROP TABLE [dbo].[USERS_TABLE]
GO
/****** Object:  Table [dbo].[USERS_TABLE]    Script Date: 11/16/2010 14:15:53 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[USERS_TABLE]') AND type in (N'U'))
BEGIN
CREATE TABLE [dbo].[USERS_TABLE](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[FIRST_NAME] [char](40) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	[LAST_NAME] [char](40) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
 CONSTRAINT [PK_USERS_TABLE] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON)
)
END
GO
SET IDENTITY_INSERT [dbo].[USERS_TABLE] ON
INSERT [dbo].[USERS_TABLE] ([ID], [FIRST_NAME], [LAST_NAME]) VALUES (1, N'fn                                      ', N'ln                                      ')
INSERT [dbo].[USERS_TABLE] ([ID], [FIRST_NAME], [LAST_NAME]) VALUES (2, N'aa                                      ', N'aa                                      ')
INSERT [dbo].[USERS_TABLE] ([ID], [FIRST_NAME], [LAST_NAME]) VALUES (3, N'bb                                      ', N'bb                                      ')
INSERT [dbo].[USERS_TABLE] ([ID], [FIRST_NAME], [LAST_NAME]) VALUES (4, N'gg                                      ', N'gg                                      ')
INSERT [dbo].[USERS_TABLE] ([ID], [FIRST_NAME], [LAST_NAME]) VALUES (5, N'ff                                      ', N'ff                                      ')
SET IDENTITY_INSERT [dbo].[USERS_TABLE] OFF
/****** Object:  Table [dbo].[INVENTORY_TABLE]    Script Date: 11/16/2010 14:15:53 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[INVENTORY_TABLE]') AND type in (N'U'))
BEGIN
CREATE TABLE [dbo].[INVENTORY_TABLE](
	[ID] [char](20) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	[PRICE] [float] NULL,
	[DESCRIPTION] [nchar](40) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	[INSTOCK] [int] NULL,
	[RESERVED] [int] NULL,
 CONSTRAINT [PK_INVENTORY_TABLE] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON)
)
END
GO
INSERT [dbo].[INVENTORY_TABLE] ([ID], [PRICE], [DESCRIPTION], [INSTOCK], [RESERVED]) VALUES (N'CPU-01              ', 123.5, N'Pentium IV                              ', 8, 10)
INSERT [dbo].[INVENTORY_TABLE] ([ID], [PRICE], [DESCRIPTION], [INSTOCK], [RESERVED]) VALUES (N'CPU-02              ', 123.45, N'asdsd                                   ', 24, 3)
INSERT [dbo].[INVENTORY_TABLE] ([ID], [PRICE], [DESCRIPTION], [INSTOCK], [RESERVED]) VALUES (N'CPU-03              ', 12, N'ss                                      ', 60, 0)
INSERT [dbo].[INVENTORY_TABLE] ([ID], [PRICE], [DESCRIPTION], [INSTOCK], [RESERVED]) VALUES (N'HDD-01              ', 223.41, N'Quantum 80GB IDE HDD                    ', 15, 5)
INSERT [dbo].[INVENTORY_TABLE] ([ID], [PRICE], [DESCRIPTION], [INSTOCK], [RESERVED]) VALUES (N'HDD-02              ', 333.33, N'1600 GB HDD                             ', 5, 0)
