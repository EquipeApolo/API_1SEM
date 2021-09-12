import MySQLdb

con = MySQLdb.connect(db='test', user='root', passwd='')
cur = con.cursor()
cur.execute('CREATE TABLE SPC(CPF/CNPJ NUMBER(15) NOT Null Constraint PK_CPF/CNPJ Primary Key,Nome/NOM_COM char(255) NOT Null,NOM_RAZ_SCL char(255) NOT Null,pessoa jurídica/fisica varchar(1) NOT Null,estado financeiro char(2) NOT Null,COD_MDL char(3) Not Null,DES_MDL char(255),VLR_SDO_UTZ_CRD_RTO number(9,2),VLR_TOT_FAT number(9,2),VLR_MIM_FAT number(9,2),VLR_PCL_FAT number(9,2),QTD_CLI_CAD_POS number(4),QTD_MVT number(4),VLR_CTRD_CSC number(9,2),QTD_PCL number(4),VLR_SDO_DDR number(9,2),VLR_PGT_FAT number(9,2),DAT_VCT date,DAT_INC_DBO date,);')
recordset = cur.fetchall()
for record in recordset:
 print (record)
con.close()
