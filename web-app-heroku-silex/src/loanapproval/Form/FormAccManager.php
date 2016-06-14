<?php

namespace loanapproval\Form;

use Silex\Application;
use Symfony\Component\Validator\Constraints as Assert;
use Symfony\Component\Form\Extension\Core\Type\TextType;

/**
 * Description of FormRegister.
 *
 * @author maxime
 */
class FormAccManager
{
    public function createFormAdd(Application $app)
    {
        $form = $app['form.factory']->createBuilder()
                ->add('name', TextType::class, array(
                    'constraints' => array(new Assert\NotBlank()), ))
                ->add('surname', TextType::class, array(
                    'constraints' => array(new Assert\NotBlank()), ))
                ->add('risk', TextType::class, array(
                    'constraints' => array(new Assert\NotBlank()), ))
                ->add('account', TextType::class, array(
                    'constraints' => array(new Assert\NotBlank()), ))
                ->getForm();

        return $form;
    }
        public function createFormDelete(Application $app)
    {
        $form = $app['form.factory']->createBuilder()
                ->add('name', TextType::class, array(
                    'constraints' => array(new Assert\NotBlank()), ))
                ->getForm();

        return $form;
    }
}
