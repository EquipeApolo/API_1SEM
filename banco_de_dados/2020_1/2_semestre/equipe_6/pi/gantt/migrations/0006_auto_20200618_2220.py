# Generated by Django 3.0.7 on 2020-06-18 22:20

from django.db import migrations


class Migration(migrations.Migration):

    dependencies = [
        ('gantt', '0005_auto_20200618_0019'),
    ]

    operations = [
        migrations.AlterUniqueTogether(
            name='tb_pes_trf',
            unique_together={('fk_pes_id', 'fk_trf_id')},
        ),
        migrations.RemoveField(
            model_name='tb_pes_trf',
            name='fk_prj_id',
        ),
    ]
